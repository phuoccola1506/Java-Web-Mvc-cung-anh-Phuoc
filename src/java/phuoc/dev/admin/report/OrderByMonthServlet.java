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
import phuoc.dev.admin.BaseAdminServlet;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.OrderDao;

/**
 *
 * @author Admin
 */
@WebServlet("/OrderByMonthServlet")
public class OrderByMonthServlet extends BaseAdminServlet {

    @Override
    @SuppressWarnings("null")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String monthParam = request.getParameter("month");
        String yearParam = request.getParameter("year");

        int month, year;

        if (monthParam != null && yearParam != null) {
            month = Integer.parseInt(monthParam);
            year = Integer.parseInt(yearParam);
        } else {
            LocalDate now = LocalDate.now();
            month = now.getMonthValue();
            year = now.getYear();
        }

        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
        Map<String, Integer> orderByMonth = orderDao.countOrderByMonth(year, month);
        Map<String, Double> earningByMonth = orderDao.earningOrderByMonth(year, month);

        request.setAttribute("orderByMonth", orderByMonth);
        request.setAttribute("earningByMonth", earningByMonth);

        request.setAttribute("selectedMonth", month);
        request.setAttribute("selectedYear", year);

        request.getRequestDispatcher("admin/report/orderByMonth.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
