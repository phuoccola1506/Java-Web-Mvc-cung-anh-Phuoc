package phuoc.dev.data.impl;

import java.sql.*;
import java.time.YearMonth;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import phuoc.dev.data.dao.OrderDao;
import phuoc.dev.data.driver.MySQLDriver;
import phuoc.dev.data.model.Order;

public class OrderImpl implements OrderDao {

    private final Connection con = MySQLDriver.getInstance().getConnection();

    @Override
    public Order insert(Order order) {
        String sql = "INSERT INTO ORDERS(code, status, user_id, discount_code) VALUES(?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, order.getCode());
            stmt.setString(2, order.getStatus());
            stmt.setInt(3, order.getUserId());
            stmt.setString(4, order.getDiscountCode());

            if (stmt.executeUpdate() > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                    return order;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public boolean update(Order order) {
        String sql = "UPDATE ORDERS SET code = ?, status = ?, user_id = ?, created_at = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, order.getCode());
            stmt.setString(2, order.getStatus());
            stmt.setInt(3, order.getUserId());
            stmt.setTimestamp(4, order.getCreatedAt());
            stmt.setInt(5, order.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ORDERS WHERE ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    @Override
    public Order find(int id) {
        String sql = "SELECT * FROM ORDERS WHERE ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractOrder(rs);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM ORDERS";
        try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return orders;
    }

    @Override
    public List<Order> findByUser(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM ORDERS WHERE user_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return orders;
    }

    @Override
    public List<Order> findByStatus(String status) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM ORDERS WHERE STATUS = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(extractOrder(rs));
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return orders;
    }

    @Override
    public Order findByCode(String code) {
        String sql = "SELECT * FROM ORDERS WHERE CODE= ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractOrder(rs);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @Override
    public int countOrderByDay(String date) {
        String sql = "SELECT COUNT(*) AS count FROM orders WHERE DATE(created_at) = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public Map<String, Integer> countOrderByMonth(int year, int month) {
        Map<String, Integer> result = new LinkedHashMap<>();
        YearMonth yearMonth = YearMonth.of(year, month);

        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            String dateStr = String.format("%04d-%02d-%02d", year, month, day);
            result.put(dateStr, 0);
        }

        String sql = "SELECT DATE(created_at) AS day, COUNT(*) AS count "
                + "FROM orders WHERE YEAR(created_at) = ? AND MONTH(created_at) = ? "
                + "GROUP BY DATE(created_at)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("day"), rs.getInt("count"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public double earningOrderByDay(String date) {
        double total = 0;
        String sql = "SELECT id FROM orders WHERE DATE(created_at) = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                // Tính tổng tiền cho orderId nếu có hàm getTotal(orderId)
                // total += getTotal(orderId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    @Override
    public Map<String, Double> earningOrderByMonth(int year, int month) {
        Map<String, Double> result = new LinkedHashMap<>();

        // Tạo mặc định tất cả ngày trong tháng có doanh thu = 0
        YearMonth yearMonth = YearMonth.of(year, month);
        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            String dateStr = String.format("%04d-%02d-%02d", year, month, day);
            result.put(dateStr, 0.0);
        }

        // SQL lấy doanh thu theo từng ngày
        String sql = "SELECT DATE(created_at) AS day, SUM(total) AS total " +
                 "FROM orders " +
                 "WHERE YEAR(created_at) = ? AND MONTH(created_at) = ? " +
                 "GROUP BY DATE(created_at)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, year);
            stmt.setInt(2, month);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String day = rs.getString("day");
                double total = rs.getDouble("total");
                result.put(day, total);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return result;
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String code = rs.getString("code");
        double total = rs.getDouble("total");
        String status = rs.getString("status");
        int userId = rs.getInt("user_id");
        String discountCode = rs.getString("discount_code");
        Timestamp createdAt = rs.getTimestamp("created_at");
        return new Order(id, code, total, status, userId, discountCode, createdAt);
    }
}
