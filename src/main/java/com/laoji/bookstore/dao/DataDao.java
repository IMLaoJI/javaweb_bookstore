package com.laoji.bookstore.dao;

import com.laoji.bookstore.domain.MoneyOnMonth;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LONG on 2017/11/25.
 */
public interface DataDao {
    int insertdata(MoneyOnMonth moneyOnMonth) throws SQLException;
    List<MoneyOnMonth> getAllData()throws SQLException;
}
