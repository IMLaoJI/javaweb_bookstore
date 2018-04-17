package com.laoji.bookstore.domain;

/**
 * Created by LONG on 2017/11/23.
 */
public class MoneyOnMonth {
    public String month;
    //达成情况
    public String result_mon;
    //返利比率
    public String ratio;
    //返利金额
    public String money;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getResult_mon() {
        return result_mon;
    }

    public void setResult_mon(String result_mon) {
        this.result_mon = result_mon;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
