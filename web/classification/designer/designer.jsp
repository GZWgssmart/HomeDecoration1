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
    <title>设计师介绍</title>
    
<link rel="stylesheet" href="<%=path %>/css/xcConfirm.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/xcConfirm.js"></script>
    <link href="<%=path %>/css/amazeui.min.css" rel="stylesheet">
    <link href="<%=path %>/css/admin.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=path %>/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="<%=path %>/css/zoomify.min.css">
    <link rel="stylesheet" href="<%=path %>/css/style.css">
    <link rel="stylesheet" href="<%=path %>/css/font-awesome.min.css">
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
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>设计师</small></div>
                            </div>
                            <hr>
                            <div>
                            	<span id="error">${requestScope.error }</span>
                            </div>
                            <div>
	                            <input type="hidden" id="id" value="${requestScope.des.id }" />
	                            <input type="hidden" id="cus_id" value="${sessionScope.customer.id }" />
                                <p class="am-text-xxl am-text-center font-style">${requestScope.des.name }
                                	<c:if test="${requestScope.saved == false }">
                                		<a onclick="save_des();" id="save" type="button" class="am-btn am-btn-success am-round am-fr">收藏设计师</a>
                                	</c:if>
                                	<c:if test="${requestScope.saved == true }">
                                		<a id="save" type="button" class="am-btn am-btn-default am-round am-fr am-disabled">已收藏</a>
                                	</c:if>
                                </p>
                            </div>
                            <hr>
                            <section class="am-panel am-panel-default">

                                <header class="am-panel-hd">
                                    <h4 class="am-panel-title">详情</h4>
                                </header>
                                <div class="am-panel-bd">
                                	<div class="avatar am-align-left">
	                                    <p class="am-align-left">
	                                        <img src="<%=path %>/${requestScope.des.headicon }" class="am-img-thumbnail am-circle avatar" alt=""/>
	                                    </p>
	                                </div>
                                    <br/>
                                    <p class="am-text-lg">设计师名称：<a href="<%=path %>/BHdes/des?id=${requestScope.des.id }">${requestScope.des.name }</a></p>
                                    <p class="am-text-lg">邮箱：${requestScope.des.email }</p>
                                    <p class="am-text-lg">地址：${requestScope.des.address }</p>
                                    <p class="am-text-lg">装修风格：${requestScope.des.style }</p>
                                    <c:if test="${requestScope.des.experience == 'workexp1' }">
                                    	<p class="am-text-lg">工作经验：1~3年 </p>
                                    </c:if>
                                    <c:if test="${requestScope.des.experience == 'workexp2' }">
                                    	<p class="am-text-lg">工作经验：3~5年 </p>
                                    </c:if>
                                    <c:if test="${requestScope.des.experience == 'workexp3' }">
                                    	<p class="am-text-lg">工作经验：5~8年 </p>
                                    </c:if>
                                    <c:if test="${requestScope.des.experience == 'workexp4' }">
                                    	<p class="am-text-lg">工作经验：8年以上 </p>
                                    </c:if>
                                    <c:if test="${requestScope.des.experience == null }">
                                    	<p class="am-text-lg">工作经验：无 </p>
                                    </c:if>
                                    <hr>
                                    <blockquote class="font-style">
                                        <dl>
                                            <dt>描述</dt>
                                            <dd>${requestScope.des.des }</dd>
                                        </dl>
                                        <small>${requestScope.des.name }</small>
                                    </blockquote>
                                </div>
                            </section>
                            <hr>

                            <div class="am-cf am-padding">
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-xl">设计师案例精选</strong></div>
                            </div>

                            <div class="am-g">
                                <c:forEach items="${requestScope.desCaseTopList }" var="topCase" end="2" varStatus="status">
	                                <div class="am-u-sm-4">
	                                    <div class="am-thumbnail" id="designer">
	                                        <a href="<%=path %>/BHdes/desCase?id=${topCase.id }"><img src="<%=path %>/${topCase.image_1 }" alt="" class="img-rounded"/></a>
	                                        <div class="am-thumbnail-caption">
	                                            <h4>${topCase.name }</h4>
	                                            <p class="am-text-truncate">${topCase.des }</p>
	                                            <p>
	                                                <a href="<%=path %>/BHdes/desCase?id=${topCase.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
	                                            </p>
	                                        </div>
	                                    </div>
	                                </div>
                                </c:forEach>
                            </div>
                            <a href="<%=path %>/BHdes/desCaseMore?id=${requestScope.des.id }" type="button" class="am-btn am-btn-secondary am-round am-fr">更多案例</a>
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
<!--<![endif]-->
<script src="<%=path %>/js/amazeui.min.js"></script>
<script src="<%=path %>/js/app.js"></script>
<script src="<%=path %>/js/designer_save.js"></script>
<script src="<%=path %>/js/zoomify.min.js"></script>
<script type="text/javascript">
    $('.example img').zoomify();
</script>
</body>
</html>