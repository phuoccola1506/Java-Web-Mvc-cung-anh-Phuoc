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
import java.util.ArrayList;
import java.util.List;
import phuoc.dev.data.dao.CategoryDao;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.ProductDao;
import phuoc.dev.data.model.Category;
import phuoc.dev.data.model.Product;

/**
 *
 * @author Admin
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        List<Product> productList = new ArrayList<>();
        
        if(req.getParameter("property") != null && req.getParameter("order") != null) {
            String property = req.getParameter("property");
            String order = req.getParameter("order");
            productList = productDao.filter(categoryId, property, order);
        } else {
            productList = productDao.findByCategory(categoryId);
        }
        
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        Category category = categoryDao.find(categoryId);

        req.setAttribute("category", category);
        req.setAttribute("ProductList", productList);
        req.getRequestDispatcher("category.jsp").include(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
    }

    

}
