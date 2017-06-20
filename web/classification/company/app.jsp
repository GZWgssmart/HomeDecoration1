<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>预约表</title>
    <link href="<%=path %>/css/amazeui.min.css" rel="stylesheet">
    <link href="<%=path %>/css/admin.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=path %>/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="<%=path %>/css/zoomify.min.css">
    <link rel="stylesheet" href="<%=path %>/css/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=path %>/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path %>/css/style.css">
    <link rel="stylesheet" href="<%=path %>/css/beaut-home.css">
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

    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-g">
                <div class="am-u-sm-12 am-u-lg-10 am-u-lg-offset-1">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd">
                            <div class="am-cf am-padding">
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>预约</small></div>
                            </div>
                            <hr>
                            <div>
                                <p class="am-text-xxl am-text-center font-style">填写预约表</p>
                            </div>
                            <hr>
                            <form id="form" class="am-form am-form-horizontal">
                                <span id="error">${requestScope.error }</span>
                                <input type="hidden" id="id" name="id" value="${requestScope.company.id }">
                                <div class="am-form-group">
                                    <label for="name" class="am-u-sm-3 am-form-label">您的称呼</label>
                                    <div class="am-u-sm-6">
                                    	<c:if test="${sessionScope.customer != null }">
                                          	<input type="text" id="name" name="name" value="${sessionScope.customer.name }">
                                    	</c:if>
                                    	<c:if test="${sessionScope.customer == null }">
                                    		<input type="text" id="name" name="name" placeholder="输入你的称呼">
                                    	</c:if>
                                    </div>
                                    <div class="am-u-sm-3"></div>
                                </div>

                                <div class="am-form-group am-center">
                                    <label for="phone" class="am-u-sm-3 am-form-label">手机号码</label>
                                    <div class="am-u-sm-6">
                                    	<c:if test="${sessionScope.customer != null }">
                                          	<input type="text" id="phone" name="phone" value="${sessionScope.customer.phone }">
                                    	</c:if>
                                    	<c:if test="${sessionScope.customer == null }">
                                    		<input type="text" id="phone" name="phone" placeholder="输入你的手机号">
                                    	</c:if>
                                    </div>
                                    <div class="am-u-sm-3"></div>
                                </div>

                                <div class="am-form-group am-center">
                                    <label for="plot_name" class="am-u-sm-3 am-form-label">小区名称</label>
                                    <div class="am-u-sm-6">
                                        <c:if test="${sessionScope.customer != null }">
                                          	<input type="text" id="plot_name" name="plot_name" value="${sessionScope.customer.plot_name }">
                                    	</c:if>
                                    	<c:if test="${sessionScope.customer == null }">
                                    		<input type="text" id="plot_name" name="plot_name" placeholder="输入小区名称">
                                    	</c:if>
                                    </div>
                                    <div class="am-u-sm-3"></div>
                                </div>

                                <div class="am-form-group am-center">
                                    <label for="area" class="am-u-sm-3 am-form-label">建筑面积</label>
                                    <div class="am-u-sm-6">
                                        <input type="text" id="area" name="area" placeholder="输入建筑面积">
                                    </div>
                                    <div class="am-u-sm-3"></div>
                                </div>

                                <div class="am-form-group">
                                    <label for="way1" class="am-u-sm-3 am-form-label">装修方式</label>
                                    <div class="am-form-group am-u-sm-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="way" id="way1" checked value="whole"> 全包
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="way" id="way2" value="half"> 半包
                                        </label>
                                    </div>
                                    <div class="am-u-sm-3"></div>
                                </div>

                                <div class="am-form-group">
                                    <label for="budget" class="am-u-sm-3 am-form-label">装修预算</label>
                                    <div class="am-form-group am-u-sm-6">
                                        <label class="radio-inline">
                                            <input type="radio" name="budget" id="budget" checked value="5-8万"> 5-8万
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="budget" id="budget2" value="8-10万"> 8-10万
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="budget" id="budget3" value="10-15万"> 10-15万
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="budget" id="budget4" value="15万以上"> 15万以上
                                        </label>
                                    </div>
                                    <div class="am-u-sm-3"></div>
                                </div>

                                <div class="am-form-group">
                                    <div class="btn-width am-center">
                                        <input type="button" id="clicked" class="am-btn am-btn-primary am-round am-btn-block" value="提交预约表" onclick="appointment();"/>
                                    </div>
                                </div>
                            </form>
                            <hr>

                            <footer class="admin-content-footer">
                                <hr>
                                <p class="am-padding-left am-text-lg">© 2017 BeautHome-全国最大房屋装修平台 版权所有.</p>
                            </footer>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
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
<script src="<%=path %>/js/amazeui.dialog.min.js"></script>
<script src="<%=path %>/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="<%=path %>/js/zoomify.min.js"></script>
<script src="<%=path %>/js/app_company.js"></script>
<script type="text/javascript">
    $('.example img').zoomify();
</script>
</body>
</html>
