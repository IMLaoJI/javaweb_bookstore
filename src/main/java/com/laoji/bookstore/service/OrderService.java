package com.laoji.bookstore.service;

import com.laoji.bookstore.domain.Order;
import com.laoji.bookstore.domain.PageBean;
import com.laoji.bookstore.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by LONG on 2017/11/7.
 */
public interface OrderService {
    void save(Order order)throws SQLException;

    /**
     * 根据用户查找订单
     * @param user
     * @param currentPage
     * @param currentCount
     * @return
     * @throws SQLException
     */
     PageBean<Order> findByUid(User user,int currentPage,int currentCount) throws SQLException, InvocationTargetException, IllegalAccessException;

    /**
     * 根据oid查询订单详情
     * @param oid
     * @return
     */
    Order findByOid(String oid) throws Exception;

    /**
     * 更新订单信息
     * @param order
     */
    void updateOrderAddr(Order order);

    void updateOrderState(String r6_order);
}
