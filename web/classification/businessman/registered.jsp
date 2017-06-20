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
    <title>建材商注册</title>
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
        <h1>注册成为网站会员</h1>
        <p>全国最大房屋装修平台<br/>满足你的一切需求</p>
    </div>
    <hr />
</div>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <h1>建材商注册</h1>
        <hr>
        <br>
        <br>

        <form id="form" method="post" class="am-form am-form-horizontal">
            <span id="error">${requestScope.error }</span>
            <div class="am-form-group">
                <label for="supename" class="am-u-sm-2 am-form-label">商户名称</label>
                <div class="am-u-sm-10">
                    <input type="text" id="supename" name="supename" placeholder="输入你的商户名称">
                </div>
            </div>

            <div class="am-form-group">
                <label for="email" class="am-u-sm-2 am-form-label">注册邮箱</label>
                <div class="am-u-sm-10">
                    <input type="email" id="email" name="email" placeholder="输入你的注册邮箱">
                </div>
            </div>

            <div class="am-form-group">
                <label for="password" class="am-u-sm-2 am-form-label">注册密码</label>
                <div class="am-u-sm-10">
                    <input type="password" id="password" name="password" placeholder="输入密码">
                </div>
            </div>

            <div class="am-form-group">
                <label for="passwords" class="am-u-sm-2 am-form-label">确认密码</label>
                <div class="am-u-sm-10">
                    <input type="password" id="passwords" name="passwords" placeholder="确认密码">
                </div>
            </div>

            <div class="am-form-group">
                <label for="name" class="am-u-sm-2 am-form-label">负责人</label>
                <div class="am-u-sm-10">
                    <input type="text" id="name" name="name" placeholder="输入公司负责人姓名">
                </div>
            </div>

            <div class="am-form-group">
                <label for="phone" class="am-u-sm-2 am-form-label">手机号码</label>
                <div class="am-u-sm-10">
                    <input type="text" id="phone" name="phone" placeholder="输入手机号码">
                </div>
            </div>

            <div class="am-form-group">
                <div class="am-u-sm-10 am-u-sm-offset-2">
                    <input type="button" class="am-btn am-btn-primary am-btn-block" value="提交注册" onclick="supply_reg();"/>
                </div>
            </div>

            <div class="am-form-group">
                <div class="am-u-sm-10 am-u-sm-offset-2">
                    <a class="am-btn am-btn-default am-btn-block" href="<%=path %>/index/home">返回首页</a>
                </div>
            </div>
        </form>
        <c:import url="/model/footer.jsp"></c:import>
    </div>
</div>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=path %>/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="<%=path %>/js/amazeui.min.js"></script>
<script src="<%=path %>/js/app.js"></script>
<script src="<%=path %>/js/beaut-home.js"></script>
<script src="<%=path %>/js/supply.js"></script>
</body>
</html>
