package phuoc.dev.controller;

import phuoc.dev.util.Helper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.DiscountCodeDao;
import phuoc.dev.data.model.DiscountCode;
import phuoc.dev.data.model.OrderItem;

/**
 *
 * @author Admin
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        double total = Helper.total(cart);

        Double discountValue = (Double) session.getAttribute("discountValue");
        String discountCode = (String) session.getAttribute("discountCode");
        request.setAttribute("discountCode", discountCode);
        if (discountValue == null) {
            discountValue = 0.0;
        }

        double finalTotal = total - discountValue;
        if (finalTotal < 0) {
            finalTotal = 0;
        }

        request.setAttribute("cart", cart);
        request.setAttribute("total", total);
        request.setAttribute("discountValue", discountValue);
        request.setAttribute("finalTotal", finalTotal);
        request.getRequestDispatcher("cart.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create" ->
                createOrder(request);
            case "update" -> {
                updateOrder(request);

                String requestedWith = request.getHeader("X-Requested-With");
                boolean isAjax = "XMLHttpRequest".equals(requestedWith);

                if (isAjax) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    HttpSession session = request.getSession();
                    List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
                    double total = Helper.total(cart);

                    int productId = Integer.parseInt(request.getParameter("productId"));
                    double subtotal = 0;

                    for (OrderItem item : cart) {
                        if (item.getProductId() == productId) {
                            subtotal = item.getPrice() * item.getQuantity();
                            break;
                        }
                    }

                    response.getWriter().write("{\"status\":\"success\", \"total\":" + total + ", \"subtotal\":" + subtotal + ", \"productId\":" + productId + "}");
                    return;
                }
            }
            case "delete" -> {
                deleteOrder(request);

                String requestedWith = request.getHeader("X-Requested-With");
                boolean isAjax = "XMLHttpRequest".equals(requestedWith);

                if (isAjax) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    HttpSession session = request.getSession();
                    List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
                    double total = Helper.total(cart);

                    response.getWriter().write("{\"status\":\"deleted\", \"total\":" + total + "}");
                    return;
                }
            }
            case "applyDiscount" -> {
                String code = request.getParameter("discountCode");
                HttpSession session = request.getSession();

                if (code != null && !code.trim().isEmpty()) {
                    DiscountCodeDao discountCodeDao = DatabaseDao.getInstance().getDiscountCodeDao();
                    DiscountCode discountCode = discountCodeDao.findByCode(code.trim());

                    if (discountCode != null) {
                        java.util.Date now = new java.util.Date();

                        boolean valid = discountCode.getQuantity() > 0
                                && !now.before(discountCode.getStartDate())
                                && !now.after(discountCode.getEndDate());

                        if (valid) {
                            List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
                            if (cart == null) {
                                cart = new ArrayList<>();
                            }
                            double total = Helper.total(cart);

                            double discountValue = discountCode.getDiscountAmount();
                            if (discountCode.getDiscountPercent() > 0) {
                                discountValue += total * discountCode.getDiscountPercent() / 100.0;
                            }

                            double finalTotal = total - discountValue;
                            if (finalTotal < 0) {
                                finalTotal = 0;
                            }

                            // Lưu vào session
                            session.setAttribute("discountCode", discountCode.getCode());
                            session.setAttribute("discountValue", discountValue);
                            session.setAttribute("finalTotal", finalTotal);
                        } else {
                            session.removeAttribute("discountCode");
                            session.setAttribute("discountError", "Mã giảm giá hết hạn hoặc không hợp lệ.");
                        }
                    } else {
                        session.removeAttribute("discountCode");
                        session.setAttribute("discountError", "Mã giảm giá không tồn tại.");
                    }
                }

                response.sendRedirect("CartServlet");
                return;
            }
            case "clear" -> {
                HttpSession session = request.getSession();
                session.removeAttribute("cart"); // hoặc: session.setAttribute("cart", null);

                String requestedWith = request.getHeader("X-Requested-With");
                boolean isAjax = "XMLHttpRequest".equals(requestedWith);

                if (isAjax) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"status\":\"cleared\"}");
                    return;
                }
            }
            default ->
                throw new IllegalArgumentException("Unknown action: " + action);
        }

        response.sendRedirect("CartServlet");
    }

    private void createOrder(HttpServletRequest request) {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        double price = Double.parseDouble(request.getParameter("price"));

        OrderItem newItem = new OrderItem(quantity, price, 0, productId);

        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean isExistInCart = false;
        for (OrderItem item : cart) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + quantity);
                isExistInCart = true;
                break;
            }
        }

        if (!isExistInCart) {
            cart.add(newItem);
        }

        session.setAttribute("cart", cart);
    }

    private void updateOrder(HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart != null) {
            for (OrderItem item : cart) {
                if (item.getProductId() == productId) {
                    item.setQuantity(quantity);
                    break;
                }
            }
        }

        session.setAttribute("cart", cart);
    }

    private void deleteOrder(HttpServletRequest request) {
        int productId = Integer.parseInt(request.getParameter("productId"));

        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        if (cart != null) {
            cart.removeIf(item -> Integer.valueOf(item.getProductId()).equals(productId));
        }

        session.setAttribute("cart", cart);
    }
}
