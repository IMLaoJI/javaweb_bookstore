package com.laoji.bookstore.dao.impl;

import com.laoji.bookstore.dao.DataDao;
import com.laoji.bookstore.domain.MoneyOnMonth;
import com.laoji.bookstore.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by LONG on 2017/11/25.
 */
public class DataDaoImpl implements DataDao {
    @Override
    public int insertdata(MoneyOnMonth moneyOnMonth) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into sy2 values(?,?,?,?)";
        int update = runner.update(sql,moneyOnMonth.getMonth(),moneyOnMonth.getResult_mon(),moneyOnMonth.getRatio(),moneyOnMonth.getMoney() );
        return update;
    }

    public  List<MoneyOnMonth> getAllData() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from sy2 ";
        List<MoneyOnMonth> list = runner.query(sql, new BeanListHandler<MoneyOnMonth>(MoneyOnMonth.class));
        return  list;
    }
}
