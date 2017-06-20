<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>装修公司后台管理</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="<%=path %>/css/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=path %>/css/admin.css">
    <link rel="stylesheet" href="<%=path %>/css/beaut-home.css"/>
</head>
<body>

<c:choose>
	<c:when test="${sessionScope.customer != null ||
	 sessionScope.supply != null || sessionScope.company != null ||
	  sessionScope.admin != null || sessionScope.designer != null}">
		<c:import url="/model/menu_logined.jsp"></c:import>
	</c:when>
	<c:otherwise>
		<c:import url="/model/menu.jsp"></c:import>
	</c:otherwise>
</c:choose>

<div class="am-cf admin-main">
    <!-- sidebar start -->
    
    <c:import url="/model/company-left.jsp"></c:import>

    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>装修公司资料</small></div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-lg-10 am-u-lg-offset-1">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd">

                            <form id="form" method="post" class="am-form am-form-horizontal">
                            <span id="error">${requestScope.error }</span>
                            <input type="hidden" id="email" name="email" value="${sessionScope.company.email }">
                                <div class="am-form-group">
                                    <label for="password" class="am-u-sm-2 am-form-label">原密码</label>
                                    <div class="am-u-sm-10">
                                        <input type="password" id="password" name="password" placeholder="输入原密码">
                                    </div>
                                </div>

                            <div class="am-form-group">
                                <label for="passwords" class="am-u-sm-2 am-form-label">新密码</label>
                                <div class="am-u-sm-10">
                                    <input type="password" id="passwords" name="passwords" placeholder="输入新密码">
                                </div>
                            </div>
                            
                            <div class="am-form-group">
	                                <label for="passwords" class="am-u-sm-2 am-form-label">确认密码</label>
	                                <div class="am-u-sm-10">
	                                    <input type="password" id="passwordes" name="passwordes" placeholder="输入确认密码">
	                                </div>
	                            </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-10 am-u-sm-offset-2">
                                        <input type="button" onclick="company_pwd();" value="提交修改" class="am-btn am-btn-primary am-btn-block" />
                                    </div>
                                </div>
                            </form>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="admin-content-footer">
        <hr>
        <p class="am-padding-left">© 2058 by：尼古拉斯刘健</p>
    </footer>
</div>
<!-- content end -->

<a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=path %>/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="<%=path %>/js/amazeui.min.js"></script>
<script src="<%=path %>/js/app.js"></script>
<script src="<%=path %>/js/company.js"></script>
</body>
</html>
