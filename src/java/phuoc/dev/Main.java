/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package phuoc.dev;

import phuoc.dev.data.dao.CategoryDao;
import phuoc.dev.data.dao.DatabaseDao;
import phuoc.dev.data.dao.ProductDao;
import phuoc.dev.data.model.Category;
import phuoc.dev.data.model.Product;

public class Main {
    public static void main(String[] args) {
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        Product product = productDao.find(1);

        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        Category category = categoryDao.find(product.getCategoryId());
        
        System.out.println(category);
    }
}

