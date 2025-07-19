package phuoc.dev.data.model;

import phuoc.dev.data.dao.DatabaseDao;
import java.sql.Timestamp;

public class Order {

    private int id;
    private String code;
    private double total;
    private String status;
    private int userId;
    private String discountCode;
    private Timestamp createdAt;

    public static final String PENDING = "pending";
    public static final String FINISHED = "finish";

    public Order(String code, double total, String status, int userId, String discountCode) {
        super();
        this.code = code;
        this.total = total;
        this.status = status;
        this.userId = userId;
        this.discountCode = discountCode;
    }

    public Order(int id, String code, double total, String status, int userId,  String discountCode, Timestamp createdAt) {
        super();
        this.id = id;
        this.code = code;
        this.total = total;
        this.status = status;
        this.userId = userId;
        this.discountCode = discountCode;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
}
