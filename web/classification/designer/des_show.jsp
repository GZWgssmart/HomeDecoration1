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
    <title>个人案例</title>
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
    <c:import url="/model/designer-left.jsp"></c:import>

    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>个人案例</small></div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-lg-10 am-u-lg-offset-1">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd">

                            <form id="form" class="am-form am-form-horizontal" method="post" action="" enctype="multipart/form-data">
                                <span id="error">${requestScope.error }</span>

                                <div class="am-form-group">
                                    <label for="name" class="am-u-sm-4 am-u-sm-offset-4 am-form-label">案例名称： ${requestScope.show.name }</label>
                                </div>

                                <div class="am-form-group">
                                    <label for="polt_name" class="am-u-sm-4 am-u-sm-offset-4 am-form-label">小区名称： ${requestScope.show.plot_name }</label>
                                </div>

                                <div class="am-form-group">
                                    <label for="style" class="am-u-sm-4 am-u-sm-offset-4 am-form-label">装修风格： ${requestScope.show.style }</label>
                                </div>

                                <div class="am-form-group">
                                    <label for="des" class="am-u-sm-4 am-u-sm-offset-4 am-form-label">描述： ${requestScope.show.des }</label>
                                </div>

                                <div class="am-form-group">
                                <ul class="am-avg-sm-5 am-thumbnails">
                                    <li>
                                        <div class="am-form-group am-form-file">
                                            <img class="am-thumbnail" src="<%=path %>/${requestScope.show.image_1 }" />
                                        </div>
                                    </li>
                                    <li>
                                        <div class="am-form-group am-form-file">
                                            <img class="am-thumbnail" src="<%=path %>/${requestScope.show.image_2 }" />
                                        </div>
                                    </li>
                                    <li>
                                        <div class="am-form-group am-form-file">
                                            <img class="am-thumbnail" src="<%=path %>/${requestScope.show.image_3 }" />
                                        </div>
                                    </li>
                                    <li>
                                        <div class="am-form-group am-form-file">
                                            <img class="am-thumbnail" src="<%=path %>/${requestScope.show.image_4 }" />
                                        </div>
                                    </li>
                                    <li>
                                        <div class="am-form-group am-form-file">
                                            <img class="am-thumbnail" src="<%=path %>/${requestScope.show.image_5 }" />
                                        </div>
                                    </li>
                                </ul>
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
<script src="<%=path %>/js/jQuery.form.min.js"></script>
<script src="<%=path %>/js/designer_case.js"></script>
</body>
</html>