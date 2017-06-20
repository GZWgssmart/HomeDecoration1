<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>购物车列表</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="<%=path %>/css/amazeui.min.css"/>
    <link rel="stylesheet" href="<%=path %>/css/admin.css">
    <link rel="stylesheet" href="<%=path %>/css/beaut-home.css"/>
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
    <c:import url="/model/customer-left.jsp"></c:import>

    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>购物车</small></div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-lg-10 am-u-lg-offset-1">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd am-scrollable-horizontal">
                            <span id = "error">${requestScope.error }</span>
                            <table class="am-table am-table-bordered am-table-radius am-table-striped am-table-centered
                            am-text-nowrap">
                                <tr>
                                    <th>商品名称</th>
                                    <th>商品所属</th>
                                    <th>商品价格</th>
                                    <th>商品数量</th>
                                    <th>商品总价</th>
                                    <th>创建时间</th>
                                    <th>商品状态</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${requestScope.proList }" var="pro" varStatus="status">
	                                <tr>
	                                    <td>
	                                    	<c:choose>
	                                    		<c:when test="${pro.status =='Y' }">
	                                    			<input type="checkbox" name="checked" id="${pro.product_id }" value="${pro.cost }" />
	                                    		</c:when>
	                                    		<c:otherwise>
	                                    			<input type="checkbox" name="checked" disabled="disabled" />
	                                    		</c:otherwise>
	                                    	</c:choose>
	                                    	${pro.name }
	                                    </td>
	                                    <td><a href="<%=path %>/BHsup/sup?id=${pro.sup_id }">${pro.sup_name }</a></td>
	                                    <td>${pro.sale_price }元</td>
	                                    <td id="total${status.count}">
	                                    	<c:choose>
	                                    		<c:when test="${pro.status =='Y' }">
			                                        <a class="am-btn am-btn-xs am-btn-primary" href="javascript:void(0);" onclick="lowTotal(${status.count});">-</a>
	                                    		</c:when>
	                                    		<c:otherwise>
		                                        	<a class="am-btn am-btn-primary am-btn-xs am-disabled" href="#">-</a>
	                                    		</c:otherwise>
	                                    	</c:choose>
	                                    	<span id="span${status.count}">${pro.total }</span>
	                                    	<c:choose>
	                                    		<c:when test="${pro.status =='Y' }">
			                                        <a class="am-btn am-btn-xs am-btn-primary" href="javascript:void(0);" onclick="addTotal(${status.count});">+</a>
	                                    		</c:when>
	                                    		<c:otherwise>
			                                        <a class="am-btn am-btn-primary am-btn-xs am-disabled" href="#">+</a>
	                                    		</c:otherwise>
	                                    	</c:choose>
	                                    </td>
	                                    <td id="cost${status.count}">${pro.cost }</td>
	                                    <td>${pro.creat_time }</td>
	                                    <td>
	                                    	<c:if test="${pro.status == 'Y' }">
		                                    	热卖中
		                                    </c:if>
		                                    <c:if test="${pro.status == 'N' }">
		                                    	已下架
		                                    </c:if>
										</td>
	                                    <td>
	                                    	<c:choose>
	                                    		<c:when test="${pro.status =='Y' }">
			                                        <a class="am-btn am-btn-primary" href="<%=path %>/BHsup/pro?id=${pro.product_id }">查看</a>
	                                    		</c:when>
	                                    		<c:otherwise>
	                                    			<a class="am-btn am-btn-primary am-disabled" href="#">查看</a>
	                                    		</c:otherwise>
	                                    	</c:choose>
	                                    	<input type="hidden" id="cart_id${status.count}" value="${pro.id }" />
                                        	<a class="am-btn am-btn-primary" href="<%=path %>/cart/delete?id=${pro.id }">删除</a>
	                                    </td>
	                                </tr>
                                </c:forEach>
                            </table>
							<a class="am-btn am-btn-primary" href="javascript:void(0);" onclick="proChecked();">结算</a>
							<input id="flashes" type="hidden" class="am-btn" onclick="window.location.reload();" />
							
                            <ul class="am-pagination am-pagination-centered">
                                <c:choose>
									<c:when test="${requestScope.pageNo == 1 }">
										<li><a href="#" class="am-btn am-disabled">&laquo;首页</a></li>
	                                	<li><a href="#" class="am-btn am-disabled">上一页</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=path %>/cart/cart?pageNo=1" class="am-btn">&laquo;首页</a></li>
	                                	<li><a href="<%=path %>/cart/cart?pageNo=${requestScope.pageNo - 1 }" class="am-btn">上一页</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${requestScope.pageNo == requestScope.pageCount || requestScope.pageNo > requestScope.pageCount }">
										<li><a href="#" class="am-btn am-disabled">下一页</a></li>
	                                	<li><a href="#" class="am-btn am-active am-disabled">尾页&raquo;</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=path %>/cart/cart?pageNo=${requestScope.pageNo + 1 }" class="am-btn">下一页</a></li>
                                		<li><a href="<%=path %>/cart/cart?pageNo=${requestScope.pageCount }" class="am-btn">尾页&raquo;</a></li>
									</c:otherwise>
								</c:choose>
                                <li>${requestScope.pageNo }</li>
                                <li>/</li>
                                <li>${requestScope.pageCount }</li>
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
<script src="<%=path %>/js/cart.js"></script>
</body>
</html>