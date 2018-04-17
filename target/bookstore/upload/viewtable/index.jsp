<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>结果展示页面</title>
    <meta name="description" content="Page Preloading Effect: Re-creating the effect seen on fontface.ninja"/>
    <meta name="keywords" content="page loading, effect, initial, logo, sliding, web design, css animation, transform"/>
    <link rel="stylesheet" type="text/css" href="upload/viewtable/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="upload/viewtable/css/demo.css"/>
    <link rel="stylesheet" type="text/css" href="upload/viewtable/css/effect1.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.6.1/css/bulma.min.css">
    <script src="upload/viewtable/js/modernizr.custom.js"></script>
    <style type="text/css">
    .type{
        color: #fff;
        background-color: #209cee;
        border-radius: 52px;
        margin: 25px 15px;
        text-align: center;
    }
    .fangcha{
        color: #fff;
        background-color: #23d160;
        border-radius: 52px;
        margin: 25px 15px;
        text-align: center;
    }
    .fengdu{
        color: #000;
        background-color: #ffdd57;
        border-radius: 52px;
        margin: 25px 15px;
        text-align: center;

    }
    .piandu{
        color: #fff;
        background-color: #ff3860;
        border-radius: 52px;
        margin: 25px 15px;
        text-align: center;
    }
    </style>
</head>
<body class="demo-1">
<div id="ip-container" class="ip-container">
    <!-- initial header -->
    <header class="ip-header">
        <h1 class="ip-logo">
        </h1>
        <div class="ip-loader">
            <svg class="ip-inner" width="60px" height="60px" viewBox="0 0 80 80">
                <path class="ip-loader-circlebg"
                      d="M40,10C57.351,10,71,23.649,71,40.5S57.351,71,40.5,71 S10,57.351,10,40.5S23.649,10,40.5,10z"/>
                <path id="ip-loader-circle" class="ip-loader-circle"
                      d="M40,10C57.351,10,71,23.649,71,40.5S57.351,71,40.5,71 S10,57.351,10,40.5S23.649,10,40.5,10z"/>
            </svg>
        </div>
    </header>
    <!-- main content -->
    <div class="ip-main">

        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        <div class="columns is-mobile">
            <div class="column type">达成情况</div>
            <div class="column fangcha">方差：${fangcha1}</div>
            <div class="column piandu">偏度：${piandu1}</div>
            <div class="column fengdu">峰度：${fengdu1}</div>
        </div>
        <div class="columns is-mobile">
            <div class="column type">返利所得</div>
            <div class="column fangcha">方差：${fangcha2}</div>
            <div class="column piandu">偏度：${piandu2}</div>
            <div class="column fengdu">峰度：${fengdu2}</div>
        </div>
        <div class="columns is-mobile">
            <div class="column type">返利金额</div>
            <div class="column fangcha">方差：${fangcha3}</div>
            <div class="column piandu">偏度：${piandu3}</div>
            <div class="column fengdu">峰度：${fengdu3}</div>
        </div>


    </div>
</div>
<!-- /container -->
<script>
    var mode1 = ${mode1};
    var mode2 = ${mode2};
    var mode3 = ${mode3};
    var average1 = ${average1};
    var average2 = ${average2};
    var average3 = ${average3};
    var mid1 = ${mid1};
    var mid2 = ${mid2};
    var mid3 =${mid3};
    var fangcha1 = ${fangcha1};
    var fangcha2 = ${fangcha2};
    var fangcha3 = ${fangcha3};
    var biaozuncha1 = ${biaozuncha1};
    var biaozuncha2 = ${biaozuncha2};
    var biaozuncha3 = ${biaozuncha3};
    var piandu1 = ${piandu1};
    var piandu2 = ${piandu2};
    var piandu3 = ${piandu3};
    var fengdu1 = ${fengdu1};
    var fengdu2 = ${fengdu2};
    var fengdu3 = ${fengdu3};

</script>
<script src="upload/viewtable/js/classie.js"></script>
<script src="upload/viewtable/js/pathLoader.js"></script>
<script src="upload/viewtable/js/main.js"></script>
<script type="text/javascript" src="upload/viewtable/js/jquery-1.8.3.min.js"></script>
<script src="upload/viewtable/js/highcharts.js"></script>
<script type="text/javascript" src="upload/viewtable/js/data.js"></script>
<script type="text/javascript" src="upload/viewtable/js/drilldown.js"></script>

<script type="text/javascript" src="upload/viewtable/js/table.js"></script>
</body>
</html>