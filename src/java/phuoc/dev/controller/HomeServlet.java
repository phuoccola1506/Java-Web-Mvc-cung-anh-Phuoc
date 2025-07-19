/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuoc.dev.controller;

import phuoc.dev.data.dao.DatabaseDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import phuoc.dev.data.dao.CategoryDao;
import phuoc.dev.data.dao.ProductDao;
import phuoc.dev.data.model.Category;
import phuoc.dev.data.model.Product;

/**
 *
 * @author Admin
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        List<Product> hotProductList = productDao.hot(8);
        List<Product> newProductList = productDao.news(6);

        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();

        Map<Integer, String> categories = new HashMap<>();

        for (Product p : hotProductList) {
            int categoryId = p.getCategoryId();
            if (!categories.containsKey(categoryId)) {
                Category c = categoryDao.find(categoryId);
                categories.put(categoryId, c.getName());
            }
        }

        for (Product p : newProductList) {
            int categoryId = p.getCategoryId();
            if (!categories.containsKey(categoryId)) {
                Category c = categoryDao.find(categoryId);
                categories.put(categoryId, c.getName());
            }
        }
        
        List<Category> topCategories = categoryDao.hotCategory(5);

        req.setAttribute("HotProductList", hotProductList);
        req.setAttribute("NewProductList", newProductList);
        req.setAttribute("Categories", categories);
        req.setAttribute("TopCategories", topCategories);

        req.getRequestDispatcher("home.jsp").include(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
