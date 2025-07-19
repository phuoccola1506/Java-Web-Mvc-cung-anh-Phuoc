/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuoc.dev.data.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class DiscountCode {

    private int id;
    private String name;
    private String code;
    private int quantity;
    private double discountAmount;   // USD
    private int discountPercent;     // %
    private Date startDate;
    private Date endDate;

    public DiscountCode() {
    }

    public DiscountCode(int id, String name, String code, int quantity, double discountAmount, int discountPercent, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.quantity = quantity;
        this.discountAmount = discountAmount;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Helper method (optional): Kiểm tra mã có hợp lệ tại thời điểm hiện tại
    public boolean isValid(Date now) {
        return quantity > 0
                && (startDate == null || !now.before(startDate))
                && (endDate == null || !now.after(endDate));
    }
}
