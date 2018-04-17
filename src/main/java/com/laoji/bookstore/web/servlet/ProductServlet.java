package com.laoji.bookstore.web.servlet;

import com.google.gson.Gson;
import com.laoji.bookstore.domain.PageBean;
import com.laoji.bookstore.domain.Product;
import com.laoji.bookstore.service.ProductService;
import com.laoji.bookstore.service.impl.ProductServiceImpl;
import com.laoji.bookstore.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

public class ProductServlet extends BaseServlet {

    public void findAllProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //分页需要的数据 total rows
        int currentPage = Integer.parseInt(request.getParameter("page"));
        int currentCount = Integer.parseInt(request.getParameter("rows"));

        ProductService service = new ProductServiceImpl();

        PageBean<Product> pageBean = service.getPageBean(currentPage, currentCount);


        Gson gson = new Gson();

        String json = gson.toJson(pageBean);

        response.getWriter().write(json);
    }

    public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        String cookie = CookieUtils.getCookieValue(request, "history");
        if (cookie == null) {
            CookieUtils.setCookie(request, response, "history", pid, 7 * 24 * 60 * 60);
        } else {
            String[] ids = cookie.split("-");
            LinkedList<String> list = new LinkedList<String>(Arrays.asList(ids));
            if (list.contains(pid))
            {
                list.remove(pid);
                list.addFirst(pid);
            }else {
                if (list.size() >= 6) {
                    list.removeLast();
                    list.addFirst(pid);
                }else {
                    list.addFirst(pid);
                }
            }
            StringBuffer sb = new StringBuffer();
            for (String id : list) {
                sb.append(id + "-");
            }
            String history = sb.substring(0, sb.length() - 1);
            CookieUtils.setCookie(request, response, "history", history, 7 * 24 * 60 * 60);

        }
        ProductService productService = new ProductServiceImpl();
        Product product = null;
        try {
            product = productService.findById(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("product", product);
        request.getRequestDispatcher("/product_info.jsp").forward(request, response);
        return;
    }

    public void findByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int currentCount = 12;
        ProductService productService = new ProductServiceImpl();
        PageBean<Product> productPageBean = null;
        try {
            productPageBean = productService.findByCid(cid, currentPage, currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("pageBean", productPageBean);
        request.setAttribute("cid", cid);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }

}
