package com.laoji.bookstore.service.impl;

import com.laoji.bookstore.dao.UserDao;
import com.laoji.bookstore.dao.impl.UserDaoImpl;
import com.laoji.bookstore.domain.User;
import com.laoji.bookstore.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

	public boolean regist(User user) {

		UserDao dao = new UserDaoImpl();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return row>0?true:false;
	}

	//激活
	public boolean active(String activeCode) {
		UserDao dao =  new UserDaoImpl();
		try {
			dao.active(activeCode);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//校验用户名是否存在
	public boolean checkUsername(String username) {
		UserDao dao =  new UserDaoImpl();
		Long isExist = 0L;
		try {
			isExist = dao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist>0?true:false;
	}

	//用户登录的方法
	public User login(User user) throws SQLException {
		UserDao dao =  new UserDaoImpl();
		return dao.login(user.getUsername(),user.getPassword());
	}

}
