package com.laoji.bookstore.dao.impl;

import com.laoji.bookstore.dao.CalcuDao;
import com.laoji.bookstore.domain.MoneyOnMonth;
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by LONG on 2017/11/24.
 */
public class CalcuDaoImpl implements CalcuDao {

    //flag=0 计算money flag =1 result_mon flag=2 ratio
    public List<MoneyOnMonth> list;
    public List<Double> money = new ArrayList<Double>();
    public List<Double> result_mon = new ArrayList<Double>();
    public List<Double> ratio = new ArrayList<Double>();

    public CalcuDaoImpl(List<MoneyOnMonth> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            result_mon.add(Double.parseDouble(list.get(i).getResult_mon()));
            money.add(Double.parseDouble(list.get(i).getMoney()));
            ratio.add(Double.parseDouble(list.get(i).getRatio()));
        }
    }

    static {

    }

    @Override
    public double average(int flag) {
        List<Double> temp = new ArrayList<Double>();
        if (flag == 0) {
            temp = money;
        } else if (flag == 1) {
            temp = result_mon;
        } else if (flag == 2) {
            temp = ratio;
        }
        Double res = 0.0;
        for (Double mo : temp) {
            res += mo;
        }
        BigDecimal b = new BigDecimal(res / temp.size());
        double f1 = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public Double[] mode(int flag) {

        Double[] ins = null;
        if (flag == 0) {
            ins = this.money.toArray(new Double[0]);
        } else if (flag == 1) {
            ins = this.result_mon.toArray(new Double[0]);
        } else if (flag == 2) {
            ins = this.ratio.toArray(new Double[0]);
        }
        Map<Double, Integer> map = new HashMap<Double, Integer>();
        // 得到每个数出现的频率
        for (Double i : ins) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        // key->保存频率值，value->保存原数字，根据频率降序排序
        TreeMap<Integer, List<Double>> result = new TreeMap<Integer, List<Double>>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2) * -1;
            }
        });
        for (Double key : map.keySet()) {
            List<Double> temp = null;
            int value = map.get(key);
            if (result.containsKey(value)) {
                temp = result.get(value);
                temp.add(key);
                result.put(value, temp);
            } else {
                temp = new ArrayList<Double>();
                temp.add(key);
                result.put(value, temp);
            }
        }
        // 返回第一个元素的value（出现频率最高的数字）
        return result.firstEntry().getValue().toArray(new Double[0]);
    }


    public Double avg2(Double... args) {
        Double count = 0.0;
        for (Double db : args) {
            count += db;
        }
        return Math.round(100 * count / args.length) / 100.0;
    }

    public double mid(int flag) {
        List<Double> temp = new ArrayList<Double>();
        if (flag == 0) {
            temp = money;
        } else if (flag == 1) {
            temp = result_mon;
        } else if (flag == 2) {
            temp = ratio;
        }
        Double[] args=new Double[temp.size()];
        temp.toArray(args);
        // 排序
        Arrays.sort(args);
        int size = args.length;
        if (size % 2 == 0) {
            // 如果数组长度为偶数，则中位数取中间两个数的平均值
            return this.avg2(args[size / 2], args[size / 2 + 1]);
        } else {
            return args[size / 2 + 1];
        }
    }
    public  Double biaozuncha(int flag){
        // 标准差是根据方差来取平方根
        return Math.round(Math.sqrt(fangcha(flag))*100)/100.0;
    }
    //
    //
    public  Double fangcha(int flag){
        List<Double> temp = new ArrayList<Double>();
        if (flag == 0) {
            temp = money;
        } else if (flag == 1) {
            temp = result_mon;
        } else if (flag == 2) {
            temp = ratio;
        }
        Double[] args=new Double[temp.size()];
        temp.toArray(args);
        Double count = 0.0;
        Double avg = this.average(flag);
        for (Double i : args) {
            // 值减去平均值，然后再求平方
            count += Math.pow(i-avg, 2);
        }
        // 除以数组长度
        return Math.round(count*100/args.length)/100.0;
    }

    public  Double piandu(int flag){
        List<Double> temp = new ArrayList<Double>();
        if (flag == 0) {
            temp = money;
        } else if (flag == 1) {
            temp = result_mon;
        } else if (flag == 2) {
            temp = ratio;
        }
        double[] args=new double[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            args[i] = temp.get(i);
        }
        //temp.toArray(args);
        Skewness sk = new Skewness();
        double skvalue =sk.evaluate(args);//求取偏度
        return skvalue;

    }
    public  Double fengdu(int flag){
        List<Double> temp = new ArrayList<Double>();
        if (flag == 0) {
            temp = money;
        } else if (flag == 1) {
            temp = result_mon;
        } else if (flag == 2) {
            temp = ratio;
        }
        double[] args=new double[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            args[i] = temp.get(i);
        }
        //temp.toArray(args);
        Kurtosis sis = new Kurtosis();
        double kurvalue=sis.evaluate(args);//求取峰度
        return kurvalue;
    }
}
