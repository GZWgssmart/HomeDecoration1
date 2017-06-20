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
    <title>添加商品</title>
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
    
    <c:import url="/model/supply-left.jsp"></c:import>

    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>添加商品</small></div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-lg-10 am-u-lg-offset-1">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd">

                            <form id="form" class="am-form am-form-horizontal" method="post" action="" enctype="multipart/form-data">
                                <span id="error">${requestScope.error }</span>
                                <div class="am-form-group">
                                    <label for="name" class="am-u-sm-2 am-form-label">商品名称</label>
                                    <div class="am-u-sm-10">
                                        <input type="text" id="name" name="name" placeholder="请输入商品名称">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="price" class="am-u-sm-2 am-form-label">商品价格</label>
                                    <div class="am-u-sm-10">
                                        <input type="text" id="price" name="price" placeholder="请输入商品价格">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="sale_price" class="am-u-sm-2 am-form-label">折后价</label>
                                    <div class="am-u-sm-10">
                                        <input type="text" id="sale_price" name="sale_price" placeholder="请输入商品折后价">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="des" class="am-u-sm-2 am-form-label">商品描述</label>
                                    <div class="am-u-sm-10">
                                        <textarea class="" rows="5" id="des" name="des" placeholder="请输入商品描述"></textarea>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <ul class="am-avg-sm-1 am-thumbnails">
                                        <li>
                                            <div class="am-form-group am-form-file">
                                                <a href="#"><img class="am-thumbnail" src="<%=path %>/img/null.jpg" /></a>
                                                <input type="file" name="file" multiple>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-12">
                                        <input type="button" class="am-btn am-btn-primary am-btn-block" value="提交商品" onclick="add_pro();"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <c:import url="/model/footer.jsp"></c:import>
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
<script src="<%=path %>/js/jQuery.form.min.js"></script>
<script src="<%=path %>/js/app.js"></script>
<script src="<%=path %>/js/product.js"></script>
</body>
</html>