<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="sweetalert/sweetalert.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="sweetalert/sweetalert.css" type="text/css"/>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="css/style.css" type="text/css"/>

    <style>
        body {
            margin-top: 20px;
            margin: 0 auto;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }

        .container .row div {
            /* position:relative;
                         float:left; */

        }

        font {
            color: #666;
            font-size: 22px;
            font-weight: normal;
            padding-right: 17px;
        }
    </style>
    <script type="text/javascript">
        <%
            if (request.getAttribute("status")!=null){
                    int status = Integer.parseInt(request.getAttribute("status").toString());
                if (status==1){


        %>


        <%
        }else {
        %>
        $(function () {
            swal({
                title: "进入失败",
                text: "请先登录",
                type: "error",
                timer: 2000,
                showConfirmButton: false
            });
            setTimeout(function() {

            }, 2000);
        })
        <%
       }
       }
       %>
        function delProFromCart(pid){
            if(confirm("您是否要删除该项？")){
                location.href="${pageContext.request.contextPath }/CartServlet?method=delProFromCart&pid="+pid;
            }
        }

        function clearCart(){
            if(confirm("您是否要清空购物车？")){
                location.href="${pageContext.request.contextPath }/CartServlet?method=clearCart";
            }
        }
    </script>
</head>
<body>

<!-- 引入header.jsp -->
<jsp:include page="/header.jsp"></jsp:include>


<div class="container"
     style="width: 100%; height: 460px; background: #FF2C4C url('images/loginbg.jpg') no-repeat;">
    <div class="row">
        <div class="col-md-7">
            <!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
        </div>

        <div class="col-md-5">
            <div
                    style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
                <font>会员登录</font>USER LOGIN
                <div>&nbsp;</div>
                <form class="form-horizontal" method="post"
                      action="${pageContext.request.contextPath}/UserServlet?method=login">

                    <%--<input type="hidden" name="method" value="login">--%>

                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="请输入用户名" value="${cookie.remembermeCookie.value}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="inputPassword3" name="password"
                                   placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="code" class="col-sm-2 control-label">验证码</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="code"
                                   placeholder="请输入验证码">
                        </div>
                        <div class="col-sm-3">
                            <img src="./image/captcha.jhtml"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label> <input type="checkbox" name="autoLogin"
                                               value="autoLogin" ${not empty cookie.cookie_username? "checked='checked'":""}>
                                    自动登录
                                </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
                                    type="checkbox" name="rememberme"
                                    value="1" ${not empty cookie.remembermeCookie? "checked='checked'":""}> 记住用户名
                            </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" width="100" value="登录" name="submit"
                                   style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 引入footer.jsp -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>