package phuoc.dev.controller;

import jakarta.mail.MessagingException;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.OrderDao;
import phuoc.dev.data.dao.OrderItemDao;
import phuoc.dev.data.dao.DiscountCodeDao;
import phuoc.dev.data.dao.ProductDao;
import phuoc.dev.data.model.Order;
import phuoc.dev.data.model.OrderItem;
import phuoc.dev.data.model.User;
import phuoc.dev.data.model.Product;
import phuoc.dev.data.model.DiscountCode;
import phuoc.dev.util.MailUtil;
import phuoc.dev.util.StringHelper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
        // Không sử dụng POST cho checkout hiện tại
    }

    @SuppressWarnings("null")
    private void proccessCheckout(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            session.setAttribute("message", "Giỏ hàng trống, không thể checkout.");
            return;
        }

        // Lấy DAO
        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
        OrderItemDao orderItemDao = DatabaseDao.getInstance().getOrderItemDao();
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        DiscountCodeDao discountCodeDao = DatabaseDao.getInstance().getDiscountCodeDao();

        // Tạo đơn hàng mới
        String code = StringHelper.randomString(10);
        String discountCodeStr = (String) session.getAttribute("discountCode");
        String finalTotalStr = (String) session.getAttribute("finalTotal");
        double finalTotal = Double.parseDouble(finalTotalStr);
        Order order = new Order(code, finalTotal, "pending", user.getId(), discountCodeStr);
        order = orderDao.insert(order);

        if (order == null) {
            session.setAttribute("message", "Lỗi khi tạo đơn hàng. Vui lòng thử lại.");
            return;
        }

        double total = 0;
        StringBuilder itemDetails = new StringBuilder();

        for (OrderItem orderItem : cart) {
            // Validate dữ liệu
            if (orderItem.getQuantity() <= 0 || orderItem.getPrice() < 0) {
                continue; // Bỏ qua item không hợp lệ
            }

            // Gắn orderId vào từng item
            orderItem.setOrderId(order.getId());
            orderItemDao.insert(orderItem);

            // Tính tổng và trừ số lượng sản phẩm
            double subtotal = orderItem.getPrice() * orderItem.getQuantity();
            total += subtotal;

            int productId = orderItem.getProductId();
            Product product = productDao.find(productId);

            if (product != null) {
                int newQuantity = product.getQuantity() - orderItem.getQuantity();
                product.setQuantity(Math.max(newQuantity, 0));
                productDao.update(product);

                // Đảm bảo không bị null khi in email
//                orderItem.setProduct(product);

                itemDetails.append(String.format(
                        "- %s | SL: %d | Giá: $%.2f%n",
                        product.getName(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()
                ));
            }
        }

        // Trừ số lượng mã giảm giá nếu có
        if (discountCodeStr != null) {
            DiscountCode discountCode = discountCodeDao.findByCode(discountCodeStr);
            if (discountCode != null && discountCode.getQuantity() > 0) {
                discountCodeDao.updateQuantity(discountCodeStr, discountCode.getQuantity() - 1);
            }
        }

        // Gửi email xác nhận đơn hàng
        try {
            MailUtil.send(
                    user.getEmail(),
                    "Xác nhận đơn hàng #" + order.getCode(),
                    "Xin chào " + user.getEmail() + ",\n\n"
                    + "Cảm ơn bạn đã đặt hàng tại LugxGaming.\n"
                    + "Mã đơn hàng: " + order.getCode() + "\n\n"
                    + "Chi tiết đơn hàng:\n" + itemDetails.toString() + "\n"
                    + "Tổng cộng: $" + String.format("%.2f", total) + "\n\n"
                    + "Chúng tôi sẽ xử lý đơn hàng trong thời gian sớm nhất.\n"
                    + "Nếu có thắc mắc, vui lòng liên hệ bộ phận hỗ trợ.\n\n"
                    + "Trân trọng,\n"
                    + "Đội ngũ LugxGaming"
            );
        } catch (MessagingException e) {
            // Ghi log nếu cần, không ngắt luồng
            System.err.println("Lỗi gửi email: " + e.getMessage());
        }

        // Dọn session sau khi checkout
        session.setAttribute("message", "Checkout thành công! Đơn hàng đã được gửi đến email.");
        session.removeAttribute("cart");
        session.removeAttribute("discountCode");
        session.removeAttribute("discountValue");
        session.removeAttribute("finalTotal");
        session.removeAttribute("discountError");
    }

}
