package com.laoji.bookstore.service.impl;

import com.laoji.bookstore.dao.ProductDao;
import com.laoji.bookstore.dao.impl.ProductDaoImpl;
import com.laoji.bookstore.domain.Category;
import com.laoji.bookstore.domain.PageBean;
import com.laoji.bookstore.domain.Product;
import com.laoji.bookstore.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProductServiceImpl implements ProductService {

    public PageBean getPageBean(int currentPage, int currentCount) {

        ProductDao dao = new ProductDaoImpl();

        //返回PageBean
        try {
            //1、封装total 代表数据总条数
            PageBean<Product> pageBean = new PageBean<Product>();
            int total = dao.getCount();
            pageBean.setTotalCount(total);
            //2、当前页显示的数据
            int index = (currentPage - 1) * currentCount;
            List<Map<String, Object>> mapList = dao.findProductListByPage(index, currentCount);
            //转换封装 List<Map<String,Object>> ----> List<Product>

            List<Product> rows = new ArrayList<Product>();

            if (mapList != null) {
                for (Map<String, Object> map : mapList) {
                    Product product = new Product();
                    Category category = new Category();
                    BeanUtils.populate(product, map);
                    BeanUtils.populate(category, map);

                    product.setCategory(category);

                    rows.add(product);
                }
            }

            pageBean.setList(rows);

            return pageBean;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addProduct(Product product) {
        ProductDao dao = new ProductDaoImpl();
        int result = 0;
        try {
            result = dao.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    //获得热门商品
    public List<Product> findByHot() {

        ProductDao dao = new ProductDaoImpl();
        List<Product> hotProductList = null;
        try {
            hotProductList = dao.findByHot();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotProductList;

    }

    //获得最新商品
    public List<Product> findByNew() {
        ProductDao dao = new ProductDaoImpl();
        List<Product> newProductList = null;
        try {
            newProductList = dao.findByNew();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newProductList;
    }

    public Product findById(String pid) throws SQLException {
        ProductDao dao = new ProductDaoImpl();

        return dao.findById(pid);
    }

    public PageBean<Product> findByCid(String cid, int currentPage, int currentCount) throws SQLException {
        ProductDao dao = new ProductDaoImpl();
        int totalRecord = dao.findTotalRecordByCid(cid);
        PageBean<Product> pageBean = new PageBean<Product>(currentPage, currentCount, totalRecord);
        List<Product> data = dao.findAllByCid(cid, pageBean.getStartIndex(), pageBean.getCurrentCount());
        pageBean.setList(data);
        return pageBean;
    }

}
