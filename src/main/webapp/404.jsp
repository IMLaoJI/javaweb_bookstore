<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改商品信息</title>

    <style>
        .block-404 {
            padding: 80px 0;
            text-align: center;
        }

        .text-404 {
            font-family: "Microsoft YaHei";
            font-size: 18px;
            color: #666;
            line-height: 28px;
        }

        .text-404 a {
            color: #2fa8e1;
            text-decoration: none;
        }

        .text-404 .text-tips {
            font-size: 30px;
            line-height: 85px;
            color: #d71818;
        }
    </style>
</head>
<body>
<div id="wraper">
    <div class="block-404">
        <div><img src="img/404.png" width="455" height="379" alt=""></div>
        <div class="text-404">
            <p class="text-tips"><span>Oh~NO~</span>当前页面歇菜了！</p>
            <p>请检查你的网址是否输入正确，或回到<a href="${pageContext.request.contextPath}/IndexServlet">网站首页</a></p>
            <p>如若需要帮助，请进入 <a ui-sref="help">帮助中心</a>或联系我们提出意见！</p>
        </div>
    </div>
</div>


</body>
</html>
