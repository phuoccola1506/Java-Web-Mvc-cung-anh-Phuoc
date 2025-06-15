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
import java.util.ArrayList;
import java.util.List;
import phuoc.dev.data.model.OrderItem;

/**
 *
 * @author Admin
 */
public class CartServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<OrderItem>();
        }
        
        req.setAttribute("cart", cart);
        
        req.getRequestDispatcher("cart.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        double price = Double.parseDouble(req.getParameter("price"));
        
        OrderItem orderItem = new OrderItem(quantity, price, 0, productId);
        
        HttpSession session = req.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        
        boolean isExisInCart = false; 
        
        if (cart == null) {
            cart = new ArrayList<>();
        } else {
            for (OrderItem oi: cart) {
                if(oi.getProductId() == productId) {
                    oi.setQuantity(oi.getQuantity() + quantity);
                    isExisInCart = true;
                    break;
                }
            }
        }
        
        if(!isExisInCart) {
            cart.add(orderItem);
        }
        
        session.setAttribute("cart", cart);
        
        resp.sendRedirect("cart.jsp");
    }

}