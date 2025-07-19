package phuoc.dev.data.dao;

import phuoc.dev.data.impl.CategoryImpl;
import phuoc.dev.data.impl.DiscountCodeImpl;
import phuoc.dev.data.impl.OrderImpl;
import phuoc.dev.data.impl.OrderItemImpl;
import phuoc.dev.data.impl.ProductImpl;
import phuoc.dev.data.impl.UserImpl;

public class Database extends DatabaseDao {

    @Override
    public ProductDao getProductDao() {
        // TODO Auto-generated method stub
        return new ProductImpl();
    }

    @Override
    public CategoryDao getCategoryDao() {
        // TODO Auto-generated method stub
        return new CategoryImpl();
    }

    @Override
    public OrderItemDao getOrderItemDao() {
        // TODO Auto-generated method stub
        return new OrderItemImpl();
    }

    @Override
    public OrderDao getOrderDao() {
        // TODO Auto-generated method stub
        return new OrderImpl();
    }

    @Override
    public UserDAO getUserDao() {
        // TODO Auto-generated method stub
        return new UserImpl();
    }

    @Override
    public DiscountCodeDao getDiscountCodeDao() {
        // TODO Auto-generated method stub
        return new DiscountCodeImpl();
    }

}
