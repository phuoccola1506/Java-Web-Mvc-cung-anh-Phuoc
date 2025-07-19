/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuoc.dev.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import phuoc.dev.data.driver.MySQLDriver;
import phuoc.dev.data.dao.DiscountCodeDao;
import phuoc.dev.data.model.DiscountCode;

/**
 *
 * @author Admin
 */
public class DiscountCodeImpl implements DiscountCodeDao {

    Connection con = MySQLDriver.getInstance().getConnection();

    @Override
    public DiscountCode findByCode(String code) {
        String sql = "SELECT * FROM discount_code WHERE code = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToDiscountCode(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DiscountCode> findAll() {
        List<DiscountCode> list = new ArrayList<>();
        String sql = "SELECT * FROM discount_code ORDER BY id DESC";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToDiscountCode(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateQuantity(String code, int newQuantity) {
        String sql = "UPDATE discount_code SET quantity = ? WHERE code = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, newQuantity);
            stmt.setString(2, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm hỗ trợ ánh xạ từ ResultSet sang model DiscountCode
    private DiscountCode mapResultSetToDiscountCode(ResultSet rs) throws SQLException {
        DiscountCode dc = new DiscountCode();
        dc.setId(rs.getInt("id"));
        dc.setName(rs.getString("name"));
        dc.setCode(rs.getString("code"));
        dc.setQuantity(rs.getInt("quantity"));
        dc.setDiscountAmount(rs.getDouble("discount_amount"));
        dc.setDiscountPercent(rs.getInt("discount_percent"));
        dc.setStartDate(rs.getDate("start_date"));
        dc.setEndDate(rs.getDate("end_date"));
        return dc;
    }

}
