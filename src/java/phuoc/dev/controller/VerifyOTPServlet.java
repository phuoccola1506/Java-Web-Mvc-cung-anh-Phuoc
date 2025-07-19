package phuoc.dev.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.UserDAO;
import phuoc.dev.data.model.User;
import phuoc.dev.util.OTPUtil;

@WebServlet(name = "VerifyOTPServlet", urlPatterns = {"/VerifyOTPServlet"})
public class VerifyOTPServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        String secretKey = user.getSecretKey();

        if (secretKey == null || secretKey.isEmpty()) {
            // Tạo secretKey mới và lưu vào DB
            secretKey = OTPUtil.generateSecretKey();
            user.setSecretKey(secretKey);

            UserDAO userDAO = DatabaseDao.getInstance().getUserDao();
            userDAO.updateSecretKey(user.getEmail(), secretKey);

            session.setAttribute("user", user);
        }

        String otpAuthURL = OTPUtil.getTOTPAuthURL("YourAppName", user.getEmail(), secretKey);
        request.setAttribute("secretKey", secretKey);
        request.setAttribute("otpAuthURL", otpAuthURL);

        RequestDispatcher rd = request.getRequestDispatcher("verify-otp.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String otp = request.getParameter("otp");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getSecretKey() == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        boolean isValid = OTPUtil.verifyCode(user.getSecretKey(), otp);

        if (isValid) {
            // Đánh dấu đã xác thực thành công
            session.setAttribute("otpVerified", true);

            // Điều hướng theo role
            if ("admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("DashboardServlet");
            } else {
                response.sendRedirect("HomeServlet");
            }

        } else {
            // OTP không hợp lệ, trở lại trang xác thực
            request.setAttribute("error", "Mã OTP không hợp lệ. Vui lòng thử lại.");
            request.setAttribute("secretKey", user.getSecretKey());
            request.setAttribute("otpAuthURL", OTPUtil.getTOTPAuthURL("YourAppName", user.getEmail(), user.getSecretKey()));
            RequestDispatcher rd = request.getRequestDispatcher("verify-otp.jsp");
            rd.forward(request, response);
        }
    }
}
