package com.laoji.bookstore.web.servlet;

import com.laoji.bookstore.dao.CalcuDao;
import com.laoji.bookstore.dao.impl.CalcuDaoImpl;
import com.laoji.bookstore.domain.MoneyOnMonth;
import com.laoji.bookstore.service.DataService;
import com.laoji.bookstore.service.impl.DataServiceImpl;
import com.laoji.bookstore.utils.excelUtil.DataFormatUtilInterface;
import com.laoji.bookstore.utils.excelUtil.ExcelDataFormatException;
import com.laoji.bookstore.utils.excelUtil.MatchupData;
import com.laoji.bookstore.utils.excelUtil.SimpleExcelUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by LONG on 2017/11/24.
 */
public class DealDateServlet extends BaseServlet {
    public void dealDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        InputStream is = null;
        String destName = request.getParameter("name");
        ServletContext context = this.getServletContext();
        String path = context.getRealPath("/WEB-INF/uploadFile");
        is = new FileInputStream(path + "/" + destName);

        SimpleExcelUtil<MoneyOnMonth> seu = new SimpleExcelUtil<MoneyOnMonth>();

        List<MoneyOnMonth> cms = seu.getDataFromExcle(is, "2", 1, new MatchupData<MoneyOnMonth>() {
            @Override
            public <T> T macthData(List<Object> data, int indexOfRow, DataFormatUtilInterface formatUtil) {
                MoneyOnMonth moneyOnMonth = new MoneyOnMonth();
                if (data.get(0) != null) {
                    moneyOnMonth.setMonth(data.get(0).toString());
                } else {
                    throw new ExcelDataFormatException("{" + 0 + "}");
                }
                moneyOnMonth.setMoney(data.get(3).toString());
                moneyOnMonth.setRatio(data.get(2).toString());
                moneyOnMonth.setResult_mon(data.get(1).toString());
                return (T) moneyOnMonth;
            }
        });


        DataService dataService= new DataServiceImpl();
        dataService.insertdata(cms);
        CalcuDao cd = new CalcuDaoImpl(cms);
        //System.out.println(cd.mode(0)[0]);
        //System.out.println(cd.mode(1)[0]);
        //System.out.println(cd.mode(2)[0]);
        //System.out.println(cd.average(0));
        //System.out.println(cd.average(1));
        //System.out.println(cd.average(2));
        //System.out.println(cd.mid(0));
        //System.out.println(cd.mid(1));
        //System.out.println(cd.mid(2));
        //System.out.println(cd.fangcha(0));
        //System.out.println(cd.fangcha(1));
        //System.out.println(cd.fangcha(2));
        //System.out.println(cd.biaozuncha(0));
        //System.out.println(cd.biaozuncha(1));
        //System.out.println(cd.biaozuncha(2));
        //System.out.println(cd.piandu(0));
        //System.out.println(cd.piandu(1));
        //System.out.println(cd.piandu(2));
        //System.out.println(cd.fengdu(0));
        //System.out.println(cd.fengdu(1));
        //System.out.println(cd.fengdu(2));

        Double mode1 = cd.mode(0)[0];
        Double mode2 = cd.mode(1)[0];
        Double mode3 = cd.mode(2)[0];
        Double average1 = cd.average(0);
        Double average2 = cd.average(1);
        Double average3 = cd.average(2);
        Double mid1 = cd.mid(0);
        Double mid2 = cd.mid(1);
        Double mid3 = cd.mid(2);

        Double fangcha1 = cd.fangcha(0);
        Double fangcha2 = cd.fangcha(1);
        Double fangcha3 = cd.fangcha(2);
        Double biaozuncha1 = cd.biaozuncha(0);
        Double biaozuncha2 = cd.biaozuncha(1);
        Double biaozuncha3 = cd.biaozuncha(2);
        Double piandu1 = cd.piandu(0);
        Double piandu2 = cd.piandu(1);
        Double piandu3 = cd.piandu(2);
        Double fengdu1 = cd.fengdu(0);
        Double fengdu2= cd.fengdu(1);
        Double fengdu3 = cd.fengdu(2);
        request.setAttribute("mode1", mode1);
        request.setAttribute("mode2", mode2);
        request.setAttribute("mode3", mode3);
        request.setAttribute("average1", average1);
        request.setAttribute("average2", average2);
        request.setAttribute("average3", average3);
        request.setAttribute("mid1", mid1);
        request.setAttribute("mid2", mid2);
        request.setAttribute("mid3", mid3);
        request.setAttribute("biaozuncha1", biaozuncha1);
        request.setAttribute("biaozuncha2", biaozuncha2);
        request.setAttribute("biaozuncha3", biaozuncha3);
        request.setAttribute("fangcha1", fangcha1);
        request.setAttribute("fangcha2", fangcha2);
        request.setAttribute("fangcha3", fangcha3);
        request.setAttribute("piandu1", piandu1);
        request.setAttribute("piandu2", piandu2);
        request.setAttribute("piandu3", piandu3);
        request.setAttribute("fengdu1", fengdu1);
        request.setAttribute("fengdu2", fengdu2);
        request.setAttribute("fengdu3", fengdu3);
        request.getRequestDispatcher("/upload/viewtable/index.jsp").forward(request, response);

        return;
    }
    public void dealDatebyDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        DataService dataService= new DataServiceImpl();
        try {
            List<MoneyOnMonth> list = dataService.getAllData();
            DataService dataService1= new DataServiceImpl();
            dataService.insertdata(list);
            CalcuDao cd = new CalcuDaoImpl(list);

            Double mode1 = cd.mode(0)[0];
            Double mode2 = cd.mode(1)[0];
            Double mode3 = cd.mode(2)[0];
            Double average1 = cd.average(0);
            Double average2 = cd.average(1);
            Double average3 = cd.average(2);
            Double mid1 = cd.mid(0);
            Double mid2 = cd.mid(1);
            Double mid3 = cd.mid(2);

            Double fangcha1 = cd.fangcha(0);
            Double fangcha2 = cd.fangcha(1);
            Double fangcha3 = cd.fangcha(2);
            Double biaozuncha1 = cd.biaozuncha(0);
            Double biaozuncha2 = cd.biaozuncha(1);
            Double biaozuncha3 = cd.biaozuncha(2);
            Double piandu1 = cd.piandu(0);
            Double piandu2 = cd.piandu(1);
            Double piandu3 = cd.piandu(2);
            Double fengdu1 = cd.fengdu(0);
            Double fengdu2= cd.fengdu(1);
            Double fengdu3 = cd.fengdu(2);
            request.setAttribute("mode1", mode1);
            request.setAttribute("mode2", mode2);
            request.setAttribute("mode3", mode3);
            request.setAttribute("average1", average1);
            request.setAttribute("average2", average2);
            request.setAttribute("average3", average3);
            request.setAttribute("mid1", mid1);
            request.setAttribute("mid2", mid2);
            request.setAttribute("mid3", mid3);
            request.setAttribute("biaozuncha1", biaozuncha1);
            request.setAttribute("biaozuncha2", biaozuncha2);
            request.setAttribute("biaozuncha3", biaozuncha3);
            request.setAttribute("fangcha1", fangcha1);
            request.setAttribute("fangcha2", fangcha2);
            request.setAttribute("fangcha3", fangcha3);
            request.setAttribute("piandu1", piandu1);
            request.setAttribute("piandu2", piandu2);
            request.setAttribute("piandu3", piandu3);
            request.setAttribute("fengdu1", fengdu1);
            request.setAttribute("fengdu2", fengdu2);
            request.setAttribute("fengdu3", fengdu3);
            request.getRequestDispatcher("/upload/viewtable/index.jsp").forward(request, response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
