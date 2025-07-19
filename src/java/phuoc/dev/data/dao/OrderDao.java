package phuoc.dev.data.dao;

import java.util.List;
import java.util.Map;

import phuoc.dev.data.model.Order;

public interface OrderDao {

    public Order insert(Order order);

    public boolean update(Order order);

    public boolean delete(int id);

    public Order find(int id);

    public List<Order> findAll();

    public List<Order> findByUser(int userId);

    public Order findByCode(String code);

    public List<Order> findByStatus(String status);
    
    public int countOrderByDay(String date);
    
    public Map<String, Integer> countOrderByMonth(int year, int month);

    public double earningOrderByDay(String date);
    
    public Map<String, Double> earningOrderByMonth(int year, int month);

}
