package com.laoji.bookstore.dao;

import com.laoji.bookstore.domain.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;



public interface ProductDao {

	int getCount() throws SQLException;

	List<Map<String, Object>> findProductListByPage(int index, int currentCount) throws SQLException;

	int addProduct(Product product) throws SQLException;
	List<Product> findByHot()throws SQLException;
	List<Product> findByNew()throws SQLException;
	Product findById(String pid)throws SQLException;
	int findTotalRecordByCid(String cid)throws SQLException;
	List<Product> findAllByCid(String cid, int startIndex,int currentCount)throws SQLException;

}
