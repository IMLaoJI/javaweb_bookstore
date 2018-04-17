package com.laoji.bookstore.web.servlet;

import com.laoji.bookstore.domain.*;
import com.laoji.bookstore.service.OrderService;
import com.laoji.bookstore.service.impl.OrderServiceImpl;
import com.laoji.bookstore.utils.MyBeanUtils;
import com.laoji.bookstore.utils.PaymentUtil;
import com.laoji.bookstore.utils.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by LONG on 2017/11/7.
 */
public class OrderServlet extends BaseServlet {
    public void saveOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("status", 0);
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
            return;
        }


        Order order = new Order();
        order.setOid(UUIDUtils.getUUID());
        order.setState(1);
        order.setOrdertime(new Date());
        order.setTotal(cart.getTotal());
        order.setUser(user);
        for (CartItem cartItem : cart.getCartItemsValue()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItemid(UUIDUtils.getUUID());
            orderItem.setCount(cartItem.getBuyNum());
            orderItem.setSubtotal(cartItem.getSubtotal());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        OrderService orderService = new OrderServiceImpl();
        orderService.save(order);
        cart.clearCart();
        request.getSession().setAttribute("order", order);
        request.setAttribute("status", 1);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
        return;
    }

    public void findByUid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int currentPage = 1;
        try {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int currentCount = 3;
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.setAttribute("status", 0);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        OrderService orderService = new OrderServiceImpl();
        PageBean<Order> productPageBean = null;
        try {
            productPageBean = orderService.findByUid(user, currentPage, currentCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("pageBean", productPageBean);
        request.getRequestDispatcher("/privilege/order_list.jsp").forward(request, response);
        return;
    }


    public void findByOid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oid = request.getParameter("oid");
        OrderService orderService = new OrderServiceImpl();
        Order order = orderService.findByOid(oid);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/order_info.jsp").forward(request,response);
        return;
    }
    /**
     * 确认订单
     */
    public void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, String[]> parameterMap = request.getParameterMap();
        Order order = new Order();
        MyBeanUtils.populate(order, parameterMap);
        OrderService orderService = new OrderServiceImpl();
        orderService.updateOrderAddr(order);
        //在线支付
        //if (pd_frpId.equals("ICBC-NET-B2C")){
        //}else if(pd_frpId.equals("ABC-NET-B2C")){
        //
        //}

        //2、在线支付
		/*if(pd_FrpId.equals("ABC-NET-B2C")){
			//介入农行的接口
		}else if(pd_FrpId.equals("ICBC-NET-B2C")){
			//接入工行的接口
		}*/
        //.......

        //只接入一个接口，这个接口已经集成所有的银行接口了  ，这个接口是第三方支付平台提供的
        //接入的是易宝支付
        // 获得 支付必须基本数据
        String orderid = request.getParameter("oid");
        //String money = order.getTotal()+"";//支付金额
        String money = "0.01";//支付金额
        // 银行
        String pd_FrpId = request.getParameter("pd_FrpId");

        // 发给支付公司需要哪些数据
        String p0_Cmd = "Buy";
        String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
        String p2_Order = orderid;
        String p3_Amt = money;
        String p4_Cur = "CNY";
        String p5_Pid = "";
        String p6_Pcat = "";
        String p7_Pdesc = "";
        // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
        // 第三方支付可以访问网址
        String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
        String p9_SAF = "";
        String pa_MP = "";
        String pr_NeedResponse = "1";
        // 加密hmac 需要密钥
        String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
                "keyValue");
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                pd_FrpId, pr_NeedResponse, keyValue);


        String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId="+pd_FrpId+
                "&p0_Cmd="+p0_Cmd+
                "&p1_MerId="+p1_MerId+
                "&p2_Order="+p2_Order+
                "&p3_Amt="+p3_Amt+
                "&p4_Cur="+p4_Cur+
                "&p5_Pid="+p5_Pid+
                "&p6_Pcat="+p6_Pcat+
                "&p7_Pdesc="+p7_Pdesc+
                "&p8_Url="+p8_Url+
                "&p9_SAF="+p9_SAF+
                "&pa_MP="+pa_MP+
                "&pr_NeedResponse="+pr_NeedResponse+
                "&hmac="+hmac;

        //重定向到第三方支付平台
        response.sendRedirect(url);

    }

}
