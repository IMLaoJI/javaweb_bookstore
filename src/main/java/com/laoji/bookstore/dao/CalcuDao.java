package com.laoji.bookstore.dao;

/**
 * Created by LONG on 2017/11/24.
 */
public interface CalcuDao {
    double average(int flag);

    Double[] mode(int flag);

    double mid(int flag);

    Double fangcha(int flag);

    Double biaozuncha(int flag);
    Double piandu(int flag);
    Double fengdu(int flag);
}
