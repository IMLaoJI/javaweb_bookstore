package com.laoji.bookstore.web.servlet;

import com.google.gson.Gson;
import com.laoji.bookstore.domain.Category;
import com.laoji.bookstore.domain.PageBean;
import com.laoji.bookstore.service.CategoryService;
import com.laoji.bookstore.service.impl.CategoryServiceImpl;
import com.laoji.bookstore.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryServlet extends BaseServlet {

    public void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得cid
        String cid = request.getParameter("cid");
        //获得cname
        String cname = request.getParameter("cname");
        Category category = new Category();
        category.setCid(cid);
        category.setCname(cname);

        CategoryService service = new CategoryServiceImpl();
        boolean isEditSuccess = service.editCategory(category);

        response.getWriter().write(isEditSuccess+"");

    }



    public void findCategoryByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受cid
        String cid = request.getParameter("cid");
        CategoryService service = new CategoryServiceImpl();
        Category category = service.findCategoryByCid(cid);

        Gson gson = new Gson();
        String json = gson.toJson(category);

        response.getWriter().write(json);

    }


    public void delCategoryByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");

        CategoryService service = new CategoryServiceImpl();
        boolean isDelSuccess = service.delCategoryByCid(cid);

        response.getWriter().write(isDelSuccess+"");

    }


    public void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得类别的名称
        String cname = request.getParameter("cname");
        Category category = new Category();
        category.setCid(UUIDUtils.getUUID());
        category.setCname(cname);

        CategoryService service = new CategoryServiceImpl();
        boolean isAddSuccess = service.addCategory(category);

        response.getWriter().write(isAddSuccess+"");

    }

    //获得全部category
    public void findAllCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService service = new CategoryServiceImpl();
        String categoryList = null;//获得全部数据
        try {
            categoryList = service.findAllByAjax();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Gson gson = new Gson();
        //String json = gson.toJson(categoryList);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(categoryList);
    }

    public void findCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryService service = new CategoryServiceImpl();
        //List<Category> categoryList = service.findCategoryList();//获得全部数据

        //分页：{"total":8,rows:[{"cid":"100","cname":"手机数码"},{},{}]}
		/*
		 * 上述json通过java对象描述
		 * 	PageBean
		 * 		private int total;
		 * 		private List<Category> rows;
		 */

        //参数1：当前页
        String currentPageStr = request.getParameter("page");
        int currentPage = Integer.parseInt(currentPageStr);
        //参数2：每页显示的条数
        String currentCountStr = request.getParameter("rows");
        int currentCount = Integer.parseInt(currentCountStr);

        PageBean<Category> pageBean = service.getPageBean(currentPage,currentCount);

        Gson gson = new Gson();
        String json = gson.toJson(pageBean);

        response.getWriter().write(json);

    }

}
