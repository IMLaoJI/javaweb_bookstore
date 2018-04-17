package com.laoji.bookstore.service;

import com.laoji.bookstore.domain.MoneyOnMonth;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LONG on 2017/11/25.
 */
public interface DataService {
    boolean insertdata(List<MoneyOnMonth> moneyOnMonths);
    List<MoneyOnMonth> getAllData() throws SQLException;
}
