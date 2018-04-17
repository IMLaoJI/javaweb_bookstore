package com.laoji.bookstore.dao;

import com.laoji.bookstore.domain.Order;
import com.laoji.bookstore.domain.OrderItem;
import com.laoji.bookstore.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by LONG on 2017/11/7.
 */
public interface OrderDao {
    void save(Connection conn, Order order)throws SQLException;
    void save(Connection conn, OrderItem orderItem)throws SQLException;
    int findTotalRecordByUid(User user)throws SQLException;
    List<Order> findAllByUid(User user, int startIndex, int currentCount) throws SQLException, InvocationTargetException, IllegalAccessException;
    Order findByOid(String oid)throws Exception;

    void updateOrderAddr(Order order) throws SQLException;

    void updateOrderState(String r6_order) throws SQLException;
}
