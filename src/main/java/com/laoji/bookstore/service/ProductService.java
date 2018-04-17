package com.laoji.bookstore.service;


import com.laoji.bookstore.domain.PageBean;
import com.laoji.bookstore.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

	PageBean getPageBean(int currentPage, int currentCount);

	boolean addProduct(Product product);
	List<Product> findByHot()throws SQLException;
	List<Product> findByNew()throws SQLException;
	Product findById(String pid)throws SQLException;
	PageBean<Product> findByCid(String cid,int currentPage,int currentCount)throws SQLException;
}
