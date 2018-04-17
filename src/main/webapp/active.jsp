<%--
  Created by IntelliJ IDEA.
  User: LONG
  Date: 2017/11/6
  Time: 下午 18:58:05  
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="sweetalert/sweetalert.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="sweetalert/sweetalert.css" type="text/css"/>
    <script type="text/javascript">
        <%

           int status = Integer.parseInt(request.getSession().getAttribute("status").toString());
            if (status==1){
            %>
        $(function () {
            swal({
                title: "激活成功",
                text: "将自动跳转登陆页",
                type: "success",
                timer: 2000,
                showConfirmButton: false
            });
            setTimeout(function() {
                window.location.href="login.jsp";;
            }, 2000);
        })

        <%
        }else {
        %>
             $(function () {
            swal({
                title: "激活失败",
                text: "将自动跳转登陆页",
                type: "error",
                timer: 2000,
                showConfirmButton: false
            });
            setTimeout(function() {
                window.location.href="login.jsp";;
            }, 2000);
        })
        <%
       }
       %>
        </script>
</head>


<body>

</body>
</html>
