/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuoc.dev.admin.order;

import phuoc.dev.admin.BaseAdminServlet;
import phuoc.dev.data.dao.CategoryDao;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.OrderDao;
import phuoc.dev.data.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import phuoc.dev.data.dao.UserDAO;
import phuoc.dev.data.model.User;

/**
 *
 * @author Welcome
 */
@WebServlet("/IndexOrderServlet")
public class IndexOrderServlet extends BaseAdminServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
        List<Order> orderList = orderDao.findAll();
        UserDAO userDao = DatabaseDao.getInstance().getUserDao();
        Map<Integer, User> userMap = new HashMap<>();

        for (Order order : orderList) {
            int userId = order.getUserId();
            if (!userMap.containsKey(userId)) {
                User user = userDao.find(userId);
                userMap.put(userId, user);
            }
        }

        request.setAttribute("orderList", orderList);
        request.setAttribute("userMap", userMap);
        request.getRequestDispatcher("admin/order/index.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
