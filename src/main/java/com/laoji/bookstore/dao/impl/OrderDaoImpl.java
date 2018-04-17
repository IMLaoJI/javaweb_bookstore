package com.laoji.bookstore.dao.impl;

import com.laoji.bookstore.dao.OrderDao;
import com.laoji.bookstore.domain.Order;
import com.laoji.bookstore.domain.OrderItem;
import com.laoji.bookstore.domain.Product;
import com.laoji.bookstore.domain.User;
import com.laoji.bookstore.utils.DataSourceUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by LONG on 2017/11/7.
 */
public class OrderDaoImpl implements OrderDao {
    public void save(Connection conn, Order order) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        Object[] params = {
                order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(),
                order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid()
        };
        int update = runner.update(conn, sql, params);
        return;
    }

    public void save(Connection conn, OrderItem orderItem) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "insert into orderitem values(?,?,?,?,?)";
        Object[] params = {
                orderItem.getItemid(), orderItem.getCount(), orderItem.getSubtotal(),
                orderItem.getProduct().getPid(), orderItem.getOrder().getOid()
        };
        int update = runner.update(conn, sql, params);
        return;
    }

    public int findTotalRecordByUid(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from orders where uid=?";
        Long count = (Long) runner.query(sql, new ScalarHandler(), user.getUid());
        return count.intValue();
    }

    public List<Order> findAllByUid(User user, int startIndex, int currentCount) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from orders where uid=?  order by ordertime desc limit ?,?";
        List<Order> list = runner.query(sql, new BeanListHandler<Order>(Order.class), user.getUid(), startIndex, currentCount);
        for (Order order : list) {
            sql = "SELECT * FROM orderitem o, product p WHERE oid=? AND o.pid=p.pid";
            List<Map<String, Object>> oList = runner.query(sql, new MapListHandler(), order.getOid());
            for (Map<String, Object> map : oList) {
                OrderItem orderItem = new OrderItem();
                BeanUtils.populate(orderItem, map);
                Product product = new Product();
                //MyBeanUtils.populate(Product.class, map);
                //BeanUtils.populate(product, map);
                product.setPid((String) map.get("pid"));
                product.setPname((String) map.get("pname"));
                product.setMarket_price((Double) map.get("market_price"));
                product.setShop_price((Double) map.get("shop_price"));
                product.setPimage((String) map.get("pimage"));
                product.setPdate((Date) map.get("pdate"));

                product.setIs_hot((Integer) map.get("is_hot"));
                product.setPdesc((String) map.get("pdesc"));
                product.setPflag((Integer) map.get("pflag"));
                orderItem.setProduct(product);
                orderItem.setOrder(order);
                order.getOrderItems().add(orderItem);
            }
            order.setUser(user);
        }
        return list;
    }

    public Order findByOid(String oid) throws Exception {

        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from orders where oid=?";
        Order order = runner.query(sql, new BeanHandler<Order>(Order.class), oid);
        sql = "SELECT * FROM orderitem o, product p WHERE o.oid=? AND o.pid=p.pid";
        List<Map<String, Object>> oList = runner.query(sql, new MapListHandler(), oid);
        for (Map<String, Object> map : oList) {
            OrderItem orderItem = new OrderItem();
            BeanUtils.populate(orderItem, map);
            Product product = new Product();
            //MyBeanUtils.populate(Product.class, map);
            //BeanUtils.populate(product, map);
            product.setPid((String) map.get("pid"));
            product.setPname((String) map.get("pname"));
            product.setMarket_price((Double) map.get("market_price"));
            product.setShop_price((Double) map.get("shop_price"));
            product.setPimage((String) map.get("pimage"));
            product.setPdate((Date) map.get("pdate"));

            product.setIs_hot((Integer) map.get("is_hot"));
            product.setPdesc((String) map.get("pdesc"));
            product.setPflag((Integer) map.get("pflag"));
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }

        return order;
    }

    public void updateOrderAddr(Order order) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update orders set address=?,name=?,telephone=? where oid=?";
        int update = runner.update(sql, order.getAddress(), order.getName(), order.getTelephone(),order.getOid());
        return;
    }

    public void updateOrderState(String r6_order) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update orders set state=? where oid=?";
        runner.update(sql, 1,r6_order);

    }
}
