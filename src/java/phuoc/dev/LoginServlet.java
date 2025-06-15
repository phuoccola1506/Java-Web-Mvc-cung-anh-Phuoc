/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuoc.dev;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.UserDAO;
import phuoc.dev.data.model.User;

/**
 *
 * @author Admin
 */
public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("HomeServlet");
        } else {
            req.getRequestDispatcher("login.jsp").include(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = DatabaseDao.getInstance().getUserDao();
        User user = userDAO.find(email, password);

        if (user == null) {
            session.setAttribute("error", "Login failed");
            resp.sendRedirect("LoginServlet");
        } else {
            session.setAttribute("user", user);
            if (user.getRole().equals("admin")) {
                resp.sendRedirect("DashboardServlet");
            } else {
                resp.sendRedirect("HomeServlet");
            }
        }
    }

}
