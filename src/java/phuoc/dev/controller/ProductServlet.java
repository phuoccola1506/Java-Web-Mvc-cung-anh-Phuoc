/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuoc.dev.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import phuoc.dev.data.dao.CategoryDao;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.ProductDao;
import phuoc.dev.data.model.Category;
import phuoc.dev.data.model.Product;

/**
 *
 * @author Admin
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        Product product = productDao.find(productId);
        
        productDao.updateView(product);

        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        Category category = categoryDao.find(product.getCategoryId());

        req.setAttribute("category", category);
        req.setAttribute("product", product);

        List<Product> newProductList = productDao.news(5);
        Map<Integer, String> categories = new HashMap<>();

        for (Product p : newProductList) {
            int categoryId = p.getCategoryId();
            if (!categories.containsKey(categoryId)) {
                Category c = categoryDao.find(categoryId);
                categories.put(categoryId, c.getName());
            }
        }

        req.setAttribute("NewProductList", newProductList);
        req.setAttribute("Categories", categories);

        req.getRequestDispatcher("product.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
