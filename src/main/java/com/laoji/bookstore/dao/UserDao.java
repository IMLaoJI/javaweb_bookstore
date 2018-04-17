package com.laoji.bookstore.dao;

import com.laoji.bookstore.domain.User;

import java.sql.SQLException;

/**
 * Created by LONG on 2017/11/6.
 */
public interface UserDao {
    public int regist(User user) throws SQLException;
    public void active(String activeCode) throws SQLException;
    public Long checkUsername(String username) throws SQLException;
    public User login(String username, String password) throws SQLException;

}
