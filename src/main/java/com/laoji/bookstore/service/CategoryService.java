
package com.laoji.bookstore.service;

import com.laoji.bookstore.domain.Category;
import com.laoji.bookstore.domain.PageBean;

import java.util.List;


public interface CategoryService {

	List<Category> findCategoryList();

	PageBean getPageBean(int currentPage, int currentCount);

	boolean addCategory(Category category);

	boolean delCategoryByCid(String cid);

	Category findCategoryByCid(String cid);

	boolean editCategory(Category category);
	String findAllByAjax() throws Exception;

}
