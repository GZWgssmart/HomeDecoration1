<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>账号审核中</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="<%=path %>/css/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=path %>/css/beaut-home.css"/>
    <style>
        .header {
            text-align: center;
        }
        .header h1 {
            font-size: 200%;
            color: #333;
            margin-top: 30px;
        }
        .header p {
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="am-g">
        <h1>欢迎来到Low狗</h1>
        <p>全国最大房屋装修平台<br/>满足你的一切需求</p>
    </div>
    <hr />
</div>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <h1>设计师登录</h1>
        <hr>
        <br>
        <br>
        <div>
            <h1 class="am-text-center">您的账号当前还在审核中，暂时无法登录，</h1>

            <h2 class="am-text-center text-red">请耐心等待审核通过。</h2>
            <br>

			<a href="<%=path %>/index/home" class="am-btn am-btn-primary am-center">回到首页</a>

        </div>
        <br>
        <br>
        <hr>
        <p>© 2017 by：尼古拉斯刘健</p>
    </div>
</div>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=path %>/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="<%=path %>/js/amazeui.min.js"></script>
<script src="<%=path %>/js/app.js"></script>
<script src="<%=path %>/js/beaut-home.js"></script>
</body>
</html>

