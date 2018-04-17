package com.laoji.bookstore.dao.impl;

import com.laoji.bookstore.dao.ProductDao;
import com.laoji.bookstore.domain.Product;
import com.laoji.bookstore.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;



public class ProductDaoImpl implements ProductDao {

	public int getCount() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product";
		
		Long query = (Long) runner.query(sql, new ScalarHandler());
		return query.intValue();
	}

	public List<Map<String,Object>> findProductListByPage(int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product p,category c where p.cid=c.cid order by p.pdate desc limit ?,?";
		List<Map<String, Object>> query = runner.query(sql, new MapListHandler(), index,currentCount);
		return query;
	}

	public int addProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		int update = runner.update(sql,product.getPid(),product.getPname(),product.getMarket_price(),
				product.getShop_price(),product.getPimage(),product.getPdate(),
				product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCategory().getCid());
		
		return update;
	}

	public List<Product> findByHot() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot=? and pflag=? order by pdate desc limit ?";
		List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class),1,0,9);
		return list;
	}

	public List<Product> findByNew() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag=? order by pdate desc limit ?";
		List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class),0,9);
		return list;
	}

	public Product findById(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

	public int findTotalRecordByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long count= (Long) runner.query(sql, new ScalarHandler(),cid);
		return count.intValue();
	}

	public List<Product> findAllByCid(String cid, int startIndex, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=? and pflag = ? order by pdate desc limit ?,?";
		List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class),cid,0,startIndex,currentCount);
		return list;
	}

}
