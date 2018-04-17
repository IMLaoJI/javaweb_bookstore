package com.laoji.bookstore.service;

import com.laoji.bookstore.domain.MoneyOnMonth;
import com.laoji.bookstore.domain.User;

import java.sql.SQLException;


public interface UserService {

	public boolean regist(User user);

	//激活
	public boolean active(String activeCode);

	//校验用户名是否存在
	public boolean checkUsername(String username);

	//用户登录的方法
	public User login(User user) throws SQLException;


}
