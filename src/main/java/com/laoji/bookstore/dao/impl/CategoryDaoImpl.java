package com.laoji.bookstore.dao.impl;

import com.laoji.bookstore.dao.CategoryDao;
import com.laoji.bookstore.domain.Category;
import com.laoji.bookstore.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;


public class CategoryDaoImpl implements CategoryDao {

	public List<Category> findCategoryList() throws SQLException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		List<Category> categoryList = runner.query(sql, new BeanListHandler<Category>(Category.class));
		
		return categoryList;
	}

	public int getTotal() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from category";
		Long query = (Long) runner.query(sql, new ScalarHandler());
		return query.intValue();
	}

	public List<Category> findCategoryListByPage(int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category limit ?,?";
		List<Category> rows = runner.query(sql, new BeanListHandler<Category>(Category.class), index,currentCount);
		return rows;
	}

	public int addCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values(?,?)";
		int update = runner.update(sql,category.getCid(),category.getCname());
		
		return update;
	}

	public int delCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category where cid=?";
		int update = runner.update(sql,cid);
		
		return update;
	}

	public Category findCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category where cid=?";
		Category category = runner.query(sql, new BeanHandler<Category>(Category.class), cid);
		return category;
	}

	public int editCategory(Category category) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update category set cname=? where cid=?";
		int result = runner.update(sql, category.getCname(),category.getCid());
		return result;
	}

}
