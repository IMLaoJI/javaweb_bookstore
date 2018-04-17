package com.laoji.bookstore.web.servlet;

import com.laoji.bookstore.domain.Cart;
import com.laoji.bookstore.domain.Product;
import com.laoji.bookstore.service.ProductService;
import com.laoji.bookstore.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by LONG on 2017/11/7.
 */
public class CartServlet extends BaseServlet {
    public void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String pid = request.getParameter("pid");
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findById(pid);
        Cart cart = getCart(request.getSession());
        cart.addCart(product,buyNum);
        request.getSession().setAttribute("cart",cart);
        response.sendRedirect(request.getContextPath()+"/cart.jsp");
        return;


    }

    private Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        return cart;
    }

    public String clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        Cart cart = getCart(request.getSession());
        cart.clearCart();
        response.sendRedirect(request.getContextPath()+"/cart.jsp");
        return null;
    }

    public String delProFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        String pid = request.getParameter("pid");
        Cart cart = getCart(request.getSession());
        cart.removeCart(pid);
        response.sendRedirect(request.getContextPath()+"/cart.jsp");
        return null;
    }

}
