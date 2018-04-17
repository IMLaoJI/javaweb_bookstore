package com.laoji.bookstore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by LONG on 2017/11/6.
 */
public class IndexServlet2 extends BaseServlet {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/upload/form_fileupload.jsp").forward(req, resp);
    }
}
