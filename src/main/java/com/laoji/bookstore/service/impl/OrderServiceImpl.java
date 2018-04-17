package com.laoji.bookstore.service.impl;

import com.laoji.bookstore.dao.OrderDao;
import com.laoji.bookstore.dao.impl.OrderDaoImpl;
import com.laoji.bookstore.domain.Order;
import com.laoji.bookstore.domain.OrderItem;
import com.laoji.bookstore.domain.PageBean;
import com.laoji.bookstore.domain.User;
import com.laoji.bookstore.service.OrderService;
import com.laoji.bookstore.utils.DataSourceUtils;
import org.apache.commons.dbutils.DbUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by LONG on 2017/11/7.
 */
public class OrderServiceImpl implements OrderService {
    public void save(Order order) throws SQLException {
        Connection conn = DataSourceUtils.getConnection();
        try {
            OrderDao orderDao = new OrderDaoImpl();
            orderDao.save(conn,order);
            for (OrderItem orderItem : order.getOrderItems()) {
                orderDao.save(conn,orderItem);
            }
            DbUtils.commitAndCloseQuietly(conn);
        } catch (Exception e) {
            DbUtils.rollbackAndCloseQuietly(conn);
            e.printStackTrace();
        }
    }

    public PageBean<Order> findByUid(User user, int currentPage, int currentCount) throws SQLException, InvocationTargetException, IllegalAccessException {
        OrderDao dao = new OrderDaoImpl();
        int totalRecord = dao.findTotalRecordByUid(user);
        PageBean<Order> pageBean = new PageBean<Order>(currentPage, currentCount, totalRecord);
        List<Order> data = dao.findAllByUid(user, pageBean.getStartIndex(), pageBean.getCurrentCount());
        pageBean.setList(data);
        return pageBean;
    }

    public Order findByOid(String oid) throws Exception {
        OrderDao orderDao = new OrderDaoImpl();
        Order byOid = orderDao.findByOid(oid);
        return byOid;
    }

    public void updateOrderAddr(Order order) {
        OrderDao orderDao = new OrderDaoImpl();
        try {
            orderDao.updateOrderAddr(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void updateOrderState(String r6_order) {
        OrderDao dao = new OrderDaoImpl();
        try {
            dao.updateOrderState(r6_order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
