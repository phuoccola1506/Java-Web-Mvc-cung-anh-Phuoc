package phuoc.dev.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import phuoc.dev.data.dao.UserDAO;
import phuoc.dev.data.driver.MySQLDriver;
import phuoc.dev.data.model.User;

public class UserImpl implements UserDAO {

    Connection con = MySQLDriver.getInstance().getConnection();

    @Override
    public boolean insert(User user) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO USERS(ID, EMAIL, PASSWORD, ROLE) VALUES(null, ?, MD5(?), ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());

            stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        // TODO Auto-generated method stub
        String sql = "UPDATE USERS SET email = ? ,password = MD5(?), role = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setInt(4, user.getId());
            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM USERS WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User find(int id) {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String secretKey = rs.getString("secret_key");

                return new User(id, email, password, role, secretKey);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE ID > ? ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, 0);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String secretKey = rs.getString("secret_key");

                users.add(new User(id, email, password, role, secretKey));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User find(String email, String password) {
        String sql = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = MD5(?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");
                String secretKey = rs.getString("secret_key");
                
                return new User(id, email, password, role, secretKey);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User find(String email) {
        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String secretKey = rs.getString("secret_key");
                
                return new User(id, email, password, role, secretKey);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateSecretKey(String email, String secretKey) {
        String sql = "UPDATE USERS SET secret_key = ? WHERE email = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, secretKey);
            stmt.setString(2, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
