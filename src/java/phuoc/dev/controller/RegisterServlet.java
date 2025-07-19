/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuoc.dev.controller;

import jakarta.mail.MessagingException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.UserDAO;
import phuoc.dev.data.model.User;
import phuoc.dev.util.MailUtil;

/**
 *
 * @author Admin
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = DatabaseDao.getInstance().getUserDao();
        User user = userDAO.find(email);

        if (user != null) {
            session.setAttribute("error", "Email đã tồn tại!");
            req.getRequestDispatcher("register.jsp").include(req, resp);
        } else {

            try {
                MailUtil.send(
                        email,
                        "Đăng ký thành công!",
                        "Xin chào,\n\nEmail " + email + " của bạn đã đăng ký tài khoản thành công tại LugxGaming.\n\nXin cảm ơn!"
                );

                // Tạo user mới
                user = new User(email, password, "user", null);
                userDAO.insert(user);
            } catch (MessagingException e) {
                // Không nên chặn người dùng nếu gửi mail lỗi
            }

            // Chuyển hướng đến Login
            resp.sendRedirect("LoginServlet");
        }
    }

}
