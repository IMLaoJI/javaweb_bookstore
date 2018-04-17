package com.laoji.bookstore.web.filter;

import com.laoji.bookstore.domain.User;
import com.laoji.bookstore.service.impl.UserServiceImpl;
import com.laoji.bookstore.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class AutoLoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        //强转成HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //如果是登录页直接放行
        String servletPath = req.getServletPath();


        if (servletPath.startsWith("/UserServlet")) {
            String method = req.getParameter("method");
            if ("loginUI".equals(method)) {
                chain.doFilter(req, resp);
                return;
            }
        }


        User user = (User) req.getSession().getAttribute("user");
        //如果已经登录 放行 不需要自动登录
        if (user != null) {
            chain.doFilter(req, resp);
            return;
        }

        String cookie_username = CookieUtils.getCookieValue(req, "cookie_username");
        //判断自动登录cookie是否存在 如果不存在 就不需要自动登录
                if (cookie_username == ""||cookie_username ==null)
                {
                    chain.doFilter(req, resp);
                    return;
                }

                String cookie_password = CookieUtils.getCookieValue(req, "cookie_password");
                User user1 = new User(cookie_username, cookie_password);
                UserServiceImpl userService = new UserServiceImpl();
                try {
                    User login = userService.login(user1);
                    //如果没有查询到 说明一个情况 就是用户已经改密码了。放行不能自动登录
                    if (login == null){
                        chain.doFilter(req, resp);
                        return;
            }
            //关键 自动登陆的实现
            req.getSession().setAttribute("user",login);
            chain.doFilter(req, resp);
        } catch (SQLException e) {
            System.out.println("自动登录异常 自动忽略");
            e.printStackTrace();
        }



    }


    public void init(FilterConfig filterConfig) throws ServletException {

    }
    //
    //public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    //    //强转成HttpServletRequest
    //    HttpServletRequest req = (HttpServletRequest) servletRequest;
    //    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    //    filterChain.doFilter(req, resp);
    //}


    public void destroy() {

    }

}
