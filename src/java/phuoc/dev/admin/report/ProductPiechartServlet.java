/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuoc.dev.admin.report;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Map;
import phuoc.dev.controller.BaseServlet;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.OrderDao;
import phuoc.dev.data.dao.ProductDao;

/**
 *
 * @author Admin
 */
@WebServlet("/ProductPiechartServlet")
public class ProductPiechartServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy tham số từ form
        String monthParam = request.getParameter("month");
        String yearParam = request.getParameter("year");

        int month, year;

        // Nếu không có tham số thì lấy tháng/năm hiện tại
        if (monthParam != null && yearParam != null) {
            month = Integer.parseInt(monthParam);
            year = Integer.parseInt(yearParam);
        } else {
            LocalDate now = LocalDate.now();
            month = now.getMonthValue();
            year = now.getYear();
        }

        // Lấy dữ liệu sản phẩm đã bán trong tháng/năm đó
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        Map<String, Integer> productData = productDao.getSoldProductsPercentage(month, year);

        // Truyền dữ liệu sang JSP
        request.setAttribute("productData", productData);
        request.setAttribute("selectedMonth", month); // Quan trọng để giữ lại lựa chọn form
        request.setAttribute("selectedYear", year);

        request.getRequestDispatcher("admin/report/productPiechart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
