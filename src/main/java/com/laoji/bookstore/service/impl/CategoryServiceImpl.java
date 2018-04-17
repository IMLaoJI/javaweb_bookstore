package com.laoji.bookstore.service.impl;

import com.laoji.bookstore.dao.CategoryDao;
import com.laoji.bookstore.dao.impl.CategoryDaoImpl;
import com.laoji.bookstore.domain.Category;
import com.laoji.bookstore.domain.PageBean;
import com.laoji.bookstore.service.CategoryService;
import com.laoji.bookstore.utils.FastJsonUtil;
import com.laoji.bookstore.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.List;


public class CategoryServiceImpl implements CategoryService {


    public List<Category> findCategoryList() {

        CategoryDao dao = new CategoryDaoImpl();
        List<Category> categoryList = null;
        try {
            categoryList = dao.findCategoryList();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryList;
    }


    public PageBean getPageBean(int currentPage, int currentCount) {
        //封装分页PageBean
        CategoryDao dao = new CategoryDaoImpl();

        try {
            PageBean<Category> pageBean = new PageBean<Category>();
            //private int total;//总条数
            int total = dao.getTotal();
            pageBean.setTotalCount(total);
            //private List<T> rows;//当前页显示的数据
            int index = (currentPage - 1) * currentCount;
            //sql:select * from category limit ?,?
            List<Category> rows = dao.findCategoryListByPage(index, currentCount);
            pageBean.setList(rows);

            return pageBean;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCategory(Category category) {
        CategoryDao dao = new CategoryDaoImpl();
        int result = 0;
        try {
            result = dao.addCategory(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }


    public boolean delCategoryByCid(String cid) {
        CategoryDao dao = new CategoryDaoImpl();
        int result = 0;
        try {
            result = dao.delCategoryByCid(cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }


    public Category findCategoryByCid(String cid) {
        CategoryDao dao = new CategoryDaoImpl();
        Category category = null;
        try {
            category = dao.findCategoryByCid(cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


    public boolean editCategory(Category category) {
        CategoryDao dao = new CategoryDaoImpl();
        int result = 0;
        try {
            result = dao.editCategory(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public String findAllByAjax() throws Exception {
        Jedis j = null;
        String value = null;
        try {
            j = JedisUtils.getJedis();
            value = j.get("category_list");
            if (value != null) {
                System.out.println("缓存中有数据");
                return value;
            }
            List<Category> clist = findCategoryList();
            value = FastJsonUtil.toJSONString(clist);
            j.set("category_list", value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisUtils.close(j);
        }

        return null;
    }


}
