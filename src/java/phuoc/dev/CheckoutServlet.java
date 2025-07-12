/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuoc.dev;

import jakarta.mail.MessagingException;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.OrderDao;
import phuoc.dev.data.dao.OrderItemDao;
import phuoc.dev.data.model.Order;
import phuoc.dev.data.model.OrderItem;
import phuoc.dev.data.model.User;
import phuoc.dev.util.StringHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import phuoc.dev.data.dao.ProductDao;
import phuoc.dev.data.model.Product;
import phuoc.dev.util.MailUtil;

/**
 *
 * @author Welcome
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("LoginServlet");
        } else {
            proccessCheckout(request, user);
            response.sendRedirect("CartServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void proccessCheckout(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();

        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
        String code = StringHelper.randomString(10);
        Order order = new Order(code, "pending", user.getId());
        orderDao.insert(order);

        order = orderDao.findByCode(code);

        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        OrderItemDao orderItemDao = DatabaseDao.getInstance().getOrderItemDao();
        double total = 0;

        StringBuilder itemDetails = new StringBuilder();

        if (cart != null) {
            for (OrderItem orderItem : cart) {
                orderItem.setOrderId(order.getId());
                orderItemDao.insert(orderItem);

                double subtotal = orderItem.getPrice() * orderItem.getQuantity();
                total += subtotal;

                int productId = orderItem.getProductId();
                ProductDao productDao = DatabaseDao.getInstance().getProductDao();
                Product product = productDao.find(productId);
                if (product != null) {
                    int newQuantity = product.getQuantity() - orderItem.getQuantity();
                    product.setQuantity(Math.max(newQuantity, 0));
                    productDao.update(product);
                }

                itemDetails.append(String.format(
                        "- %s | SL: %d | Giá: $%.2f%n",
                        orderItem.getProduct().getName(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()
                ));
            }
        }

        try {
            MailUtil.send(
                    user.getEmail(),
                    "Xác nhận đơn hàng #" + order.getCode(),
                    "Xin chào" + user.getEmail() + "\n\n"
                    + "Bạn đã đặt hàng thành công tại LugxGaming.\n"
                    + "Mã đơn hàng: " + order.getCode() + "\n\n"
                    + "Chi tiết đơn hàng:\n" + itemDetails.toString() + "\n"
                    + "Tổng cộng: " + total + "\n\n"
                    + "Chúng tôi sẽ xử lý đơn hàng trong thời gian sớm nhất.\n\n"
                    + "Trân trọng,\n"
                    + "Đội ngũ hỗ trợ khách hàng"
            );
        } catch (MessagingException e) {
            // không chặn user

        }

        session.setAttribute("message", "Checkout thành công! Đơn hàng đã được gửi đến email.");
        session.removeAttribute("cart");
    }

}
