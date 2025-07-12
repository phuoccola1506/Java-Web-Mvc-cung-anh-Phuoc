/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package phuoc.dev;

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
@WebServlet("/ShopServlet")
public class ShopServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        List<Product> productList = productDao.findAll();
        
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.hotCategory(5);
        
        Map<Integer, String> categories = new HashMap<>();

        for (Product p : productList) {
            int categoryId = p.getCategoryId();
            if (!categories.containsKey(categoryId)) {
                Category c = categoryDao.find(categoryId);
                categories.put(categoryId, c.getName());
            }
        }
        
        req.setAttribute("ProductList", productList);
        req.setAttribute("CategoryList", categoryList);
        req.setAttribute("Categories", categories);
        
        req.getRequestDispatcher("shop.jsp").include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}