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
    <title>设计师后台管理</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="<%=path %>/css/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=path %>/css/admin.css">
    <link rel="stylesheet" href="<%=path %>/css/beaut-home.css">
    <link rel="stylesheet" href="<%=path %>/css/style.css"/>
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
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>设计师资料</small></div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-lg-10 am-u-lg-offset-1">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd">

                            <a href="<%=path %>/des/headpage"><img src="<%=path %>/${sessionScope.designer.headicon}" 
                            alt="${sessionScope.designer.name }" class="am-img-thumbnail am-circle img-tx am-center" 
                            data-am-popover="{content: '点击修改头像', trigger: 'hover focus'}"></a>

                            <ul class="am-list am-list-static am-g-fixed">
                                <li>昵称：${sessionScope.designer.name }</li>
                                <li>邮箱：${sessionScope.designer.email }</li>
                                <li>手机号：${sessionScope.designer.phone }</li>
                                <li>工作经验：
                                	  <c:if test="${sessionScope.designer.experience == 'workexp1' }">
	                                  	1~3年
	                                  </c:if>
	                                  <c:if test="${sessionScope.designer.experience == 'workexp2' }">
	                                  	3~5年
	                                  </c:if>
	                                  <c:if test="${sessionScope.designer.experience == 'workexp3' }">
	                                  	5~8年
	                                  </c:if>
	                                  <c:if test="${sessionScope.designer.experience == 'workexp4' }">
	                                  	8年以上
	                                  </c:if>
	                                  <c:if test="${sessionScope.designer.experience == null }">
	                                  	无
	                                  </c:if>
                                </li>
                                <li>擅长风格：${sessionScope.designer.style }</li>
                                <li>描述：${sessionScope.designer.des }</li>
                                <li>地址：${sessionScope.designer.address }</li>
                            </ul>
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
<script src="<%=path %>/js/app.js"></script>
</body>
</html>
