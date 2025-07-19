/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package phuoc.dev.data.dao;

import java.util.List;
import phuoc.dev.data.model.DiscountCode;

/**
 *
 * @author Admin
 */
public interface DiscountCodeDao {
    DiscountCode findByCode(String code);
    
    List<DiscountCode> findAll();
    
    void updateQuantity(String code, int newQuantity);
}
