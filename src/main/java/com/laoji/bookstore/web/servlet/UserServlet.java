package com.laoji.bookstore.web.servlet;

import com.google.gson.JsonObject;
import com.laoji.bookstore.domain.User;
import com.laoji.bookstore.service.UserService;
import com.laoji.bookstore.service.impl.UserServiceImpl;
import com.laoji.bookstore.utils.MailUtils;
import com.laoji.bookstore.utils.MyBeanUtils;
import com.laoji.bookstore.utils.UUIDUtils;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends BaseServlet {
	//用户注销
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		//从session中将user删除
		session.removeAttribute("user");

		//将存储在客户端的cookie删除掉
		Cookie cookie_username = new Cookie("cookie_username","");
		cookie_username.setPath("/");
		cookie_username.setMaxAge(0);
		//创建存储密码的cookie
		Cookie cookie_password = new Cookie("cookie_password","");
		cookie_password.setMaxAge(0);
		cookie_password.setPath("/");

		response.addCookie(cookie_username);
		response.addCookie(cookie_password);


		response.sendRedirect(request.getContextPath()+"/login.jsp");

	}

	//用户登录
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		////获得输入的用户名和密码
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");
		//封装数据
		User user = MyBeanUtils.populate(User.class, request.getParameterMap());
		//将用户名和密码传递给service层
		UserService service = new UserServiceImpl();
		try {
			user = service.login(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//判断用户是否登录成功 user是否是null
		if(user!=null){
			//登录成功
			//***************判断用户是否勾选了自动登录*****************
			String autoLogin = request.getParameter("autoLogin");
			String rememberme = request.getParameter("rememberme");

			if("autoLogin".equals(autoLogin)){
				//要自动登录
				//创建存储用户名的cookie
				Cookie cookie_username = new Cookie("cookie_username",user.getUsername());
				cookie_username.setPath("/");
				cookie_username.setMaxAge(10*60);
				//创建存储密码的cookie
				Cookie cookie_password = new Cookie("cookie_password",user.getPassword());
				cookie_password.setPath("/");
				cookie_password.setMaxAge(10*60);
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);

			}else {
				Cookie cookie_username = new Cookie("cookie_username","");
				Cookie cookie_password = new Cookie("cookie_password","");
				cookie_username.setPath("/");
				cookie_username.setMaxAge(0);
				cookie_password.setPath("/");
				cookie_password.setMaxAge(0);
				response.addCookie(cookie_username);
				response.addCookie(cookie_password);
			}
			if ("1".equals(rememberme)){
				Cookie cookie_username = new Cookie("remembermeCookie",user.getUsername());
				cookie_username.setPath("/");
				cookie_username.setMaxAge(10*60);
				response.addCookie(cookie_username);
			}else {
				Cookie cookie_username = new Cookie("remembermeCookie","");
				cookie_username.setPath("/");
				cookie_username.setMaxAge(0);
				response.addCookie(cookie_username);
			}

			//***************************************************
			//将user对象存到session中
			session.setAttribute("user", user);

			//重定向到首页
			response.sendRedirect(request.getContextPath()+"/IndexServlet");
		}else{
			request.setAttribute("loginError", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}


	public void checkusername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JsonObject resultJson=new JsonObject();
		//接受文本框的值
		String username = request.getParameter("username");
		//调用业务层查询
		UserService userService = new UserServiceImpl();
		boolean isExist = userService.checkUsername(username);
		//判断:
		response.setContentType("text/html;charset=utf-8");
		if (isExist){
			resultJson.addProperty("isExist", 1);
			response.getWriter().println(resultJson);
			//response.getWriter().write("{\"isExist\":\"1\"}");
		}else {
			resultJson.addProperty("isExist", 0);
			response.getWriter().println(resultJson);
			//response.getWriter().write("{\"isExist\":\"0\"}");
		}

	}


    public void regist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
		JsonObject resultJson=new JsonObject();
        //获得数据并且封装
        User user = MyBeanUtils.populate(User.class, request.getParameterMap());
        //1.1 处理服务器自动生成属性
        user.setUid(UUIDUtils.getUUID());
		String activeCode = UUIDUtils.getUUID64();
        user.setCode(activeCode); //激活码
        user.setState(0);  //0代表未激活

        //2.处理
        UserServiceImpl userService = new UserServiceImpl();
		boolean regist = userService.regist(user);
		if (regist){
			String emailMsg = "恭喜您注册成功 请点击下面的链接进行激活"
					+"<a href='http://localhost:8090/bookstore/UserServlet?method=active&activeCode="+activeCode+"'>"
					+"http://localhost:8090/bookstore/UserServlet?method=active&activeCode="+activeCode+"</a>";
			try {
				MailUtils.sendMail(user.getEmail(),emailMsg);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			resultJson.addProperty("status", 1);
		}else {
			resultJson.addProperty("status", 0);
		}

		response.getWriter().println(resultJson);
        //成功提示
        //request.setAttribute("msg",);
		return;



    }

	public void active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		//获得激活码
		String activeCode = request.getParameter("activeCode");

		UserService service = new UserServiceImpl();
		boolean active = service.active(activeCode);
		if (active){
			request.getSession().setAttribute("status",1);
		}else {
			request.getSession().setAttribute("status",0);
		}

		//跳转到登录页面
		response.sendRedirect(request.getContextPath()+"/active.jsp");

	}
	public void loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	public void registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/register.jsp").forward(request, response);
	}

}
