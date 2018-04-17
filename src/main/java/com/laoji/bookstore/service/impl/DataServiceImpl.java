package com.laoji.bookstore.service.impl;

import com.laoji.bookstore.dao.DataDao;
import com.laoji.bookstore.dao.impl.DataDaoImpl;
import com.laoji.bookstore.domain.MoneyOnMonth;
import com.laoji.bookstore.service.DataService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LONG on 2017/11/25.
 */
public class DataServiceImpl implements DataService {
    @Override
    public boolean insertdata(List<MoneyOnMonth> moneyOnMonths) {
        DataDao dataDao = new DataDaoImpl();
        int row = 0;
        for (MoneyOnMonth mo : moneyOnMonths) {
            try {
                row = dataDao.insertdata(mo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row > 0 ? true : false;
    }

    @Override
    public List<MoneyOnMonth> getAllData() throws SQLException {
        DataDao dataDao = new DataDaoImpl();
        List<MoneyOnMonth> list = dataDao.getAllData();
        return list;
    }


}
