package phuoc.dev.admin.user;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import phuoc.dev.admin.BaseAdminServlet;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.UserDAO;
import phuoc.dev.data.model.User;

/**
 *
 * @author Admin
 */
@WebServlet("/IndexUserServlet")
public class IndexUserServlet extends BaseAdminServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDao = DatabaseDao.getInstance().getUserDao();
        List<User> userList = userDao.findAll();
        
        req.setAttribute("userList", userList);
        
        req.getRequestDispatcher("admin/user/index.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}