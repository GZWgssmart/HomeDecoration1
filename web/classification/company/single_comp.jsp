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
    <title>公司展示</title>
    <link rel="stylesheet" href="<%=path %>/css/xcConfirm.css">
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/xcConfirm.js"></script>
    <link href="<%=path %>/css/amazeui.min.css" rel="stylesheet">
    <link href="<%=path %>/css/admin.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=path %>/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="<%=path %>/css/zoomify.min.css">
    <link rel="stylesheet" href="<%=path %>/css/style.css">
    <link rel="stylesheet" href="<%=path %>/css/font-awesome.min.css">
    <style>
     #map {
     	height:300px;
     }
    </style>
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
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>装修公司</small></div>
                            </div>
                            <span id="error">${requestScope.error }</span>
                            <hr>
                            <div>
                                <p class="am-text-xxl am-text-center font-style">${requestScope.company.name }
                                    <input type="hidden" id="company_id" name="company_id" value="${requestScope.company.id }">
                                    <input type="hidden" id="customer_id" name="customer_id" value="${sessionScope.customer.id }">
                                    <input type="hidden" id="longitude" value="${requestScope.company.longitude }">
                                    <input type="hidden" id="latitude" value="${requestScope.company.latitude }">
                                    <c:if test="${requestScope.saved == false }">
                                		<a onclick="collect_company();" id="save" type="button" class="am-btn am-btn-success am-round am-fr">收藏公司</a>
                                	</c:if>
                                	<c:if test="${requestScope.saved == true }">
                                		<a id="save" type="button" class="am-btn am-btn-default am-round am-fr am-disabled">已收藏</a>
                                	</c:if>
                                	<c:if test="${requestScope.apped == false }">
                                		<a href="<%=path %>/BHcompany/appointment?id=${requestScope.company.id }" class="am-btn am-btn-success am-round am-fr">预约公司</a>
                                	</c:if>
                                	<c:if test="${requestScope.apped == true }">
                                		<a id="app" type="button" class="am-btn am-btn-default am-round am-fr am-disabled">已预约</a>
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
                                        <img src="<%=path %>/${requestScope.company.logo }" class="am-img-thumbnail am-circle avatar" alt=""/>
                                    </p>
                                    </div>
                                    <br/>
                                    <p class="am-text-lg">公司名称：<a href="<%=path %>/BHcompany/comp?id=${requestScope.company.id }">${requestScope.company.name }</a></p>
                                    <p class="am-text-lg">邮箱：${requestScope.company.email }</p>
                                    <p class="am-text-lg">地址：${requestScope.company.address }</p>
                                    <p class="am-text-lg">负责人：${requestScope.company.principal }</p>
                                    <p class="am-text-lg">联系方式：${requestScope.company.phone }</p>
                                    <hr>
                                    <blockquote class="font-style">
                                        <dl>
                                            <dt>描述</dt>
                                            <dd>${requestScope.company.des }</dd>
                                        </dl>
                                        <small>公司负责人:${requestScope.company.principal }</small>
                                    </blockquote>
                                </div>
                            </section>
							<div class="am-u-sm-12" id="map"></div>
                            <hr>

                            <div class="am-cf am-padding">
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-xl">装修公司案例精选</strong></div>
                            </div>

                            <div class="am-g">
                                <c:forEach items="${requestScope.companycase }" begin="0" end="2" var="compcase" varStatus="status" >
                                <div class="am-u-sm-4">
                                    <div class="am-thumbnail" id="company1">
                                        <a href="<%=path %>/BHcompany/case1?id=${compcase.id }"><img src="<%=path %>/${compcase.image_1 }" alt="" class="img-rounded"/></a>
                                        <div class="am-thumbnail-caption">
                                            <h4>案例名称:${compcase.name }</h4>
                                            <p>案例描述:${compcase.des }</p>
                                            <p>
                                                <a href="<%=path %>/BHcompany/case1?id=${compcase.id }" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                               </c:forEach>
                            </div>
                            <a href="<%=path %>/BHcompany/single_allcase?id=${requestScope.company.id }" class="am-btn am-btn-secondary am-round am-fr">更多案例</a>
                            <hr>

                            <div class="am-cf am-padding">
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-xl">活动</strong></div>
                            </div>
                            <div class="am-g">
                                <c:forEach items="${requestScope.companyActive }" var="compactive" varStatus="status" >
                                <div class="am-u-sm-4">
                                    <div class="am-thumbnail" id="company2">
                                        <a href="<%=path %>/BHcompany/active?id=${compactive.id }"><img src="<%=path %>/${compactive.image }" alt="" class="img-rounded"/></a>
                                        <div class="am-thumbnail-caption">
                                            <h4>活动名称:${compactive.name }</h4>
                                            <p>活动描述:${compactive.des }</p>
                                            <p>
                                                <a href="<%=path %>/BHcompany/active?id=${compactive.id }" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                               </c:forEach>
                            </div>
                            <a href="<%=path %>/BHcompany/single_allactive?id=${requestScope.company.id }" class="am-btn am-btn-secondary am-round am-fr">更多活动</a>
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
<script src="<%=path %>/js/zoomify.min.js"></script>
<script src="<%=path %>/js/collect_company.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=KjEQlPmSO1aM6G9cFGCQd7WL0arGC6zE"></script>
<script type="text/javascript">
    $('.example img').zoomify();
    
	var map = new BMap.Map("map");    // 创建Map实例
	map.centerAndZoom(new BMap.Point($("#longitude").val(),$("#latitude").val()), 13);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
</script>
</body>
</html>
