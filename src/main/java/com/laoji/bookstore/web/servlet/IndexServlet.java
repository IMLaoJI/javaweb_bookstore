package com.laoji.bookstore.web.servlet;

import com.laoji.bookstore.domain.Product;
import com.laoji.bookstore.service.ProductService;
import com.laoji.bookstore.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by LONG on 2017/11/6.
 */
public class IndexServlet extends BaseServlet {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  CategoryService categoryService = new CategoryServiceImpl();
        List<Category> allCategory = categoryService.findCategoryList();
        req.setAttribute("allCategory",allCategory);*/

        ProductService productService = new ProductServiceImpl();
        List<Product> hotList = null;
        List<Product> newList = null;
        try {
            hotList = productService.findByHot();
            newList = productService.findByNew();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("hotList", hotList);
        req.setAttribute("newList", newList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
