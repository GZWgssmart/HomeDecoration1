<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商品展示</title>
<link rel="stylesheet" href="<%=path%>/css/xcConfirm.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/xcConfirm.js"></script>
<link href="<%=path%>/css/amazeui.min.css" rel="stylesheet">
<link href="<%=path%>/css/admin.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/css/bootstrap-grid.min.css">
<link rel="stylesheet" href="<%=path%>/css/zoomify.min.css">
<link rel="stylesheet" href="<%=path%>/css/style.css">
<link rel="stylesheet" href="<%=path%>/css/font-awesome.min.css">
</head>
<body>
	<c:choose>
		<c:when
			test="${sessionScope.customer != null ||
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
									<div class="am-fl am-cf">
										<strong class="am-text-primary am-text-lg"><a
											href="javascript:history.go(-1)">返回</a></strong> / <small>商品展示</small>
									</div>
								</div>
								<hr>
								<div>
									<span id="error">${requestScope.error }</span>
								</div>
								<div>
									<input type="hidden" id="id" value="${requestScope.pro.id }" />
									<input type="hidden" id="cus_id"
										value="${sessionScope.customer.id }" />
									<p class="am-text-xxl am-text-center font-style">${requestScope.pro.name }
										<a onclick="addCart();" type="button"
											class="am-btn am-btn-warning am-round am-fr">加入购物车</a>
										<c:if test="${requestScope.saved == false }">
											<a onclick="save_pro();" id="save" type="button"
												class="am-btn am-btn-success am-round am-fr">收藏商品</a>
										</c:if>
										<c:if test="${requestScope.saved == true }">
											<a id="save" type="button"
												class="am-btn am-btn-default am-round am-fr am-disabled">已收藏</a>
										</c:if>
									</p>
								</div>
								<hr>

								<div class="container">
									<div class="examples">
										<div class="row">
											<div class="example  col-xs-12 col-md-12">
												<p>
													<img src="<%=path %>/${requestScope.pro.image }"
														class="img-rounded" alt="">
												</p>
											</div>
										</div>
									</div>
								</div>
								<hr>
								<section class="am-panel am-panel-default">

									<header class="am-panel-hd">
										<h4 class="am-panel-title">详情</h4>
									</header>
									<div class="am-panel-bd">
										<div class="avatar am-align-left">
											<p class="am-align-left">
												<img src="<%=path %>/${requestScope.sup.logo }"
													class="am-img-thumbnail am-circle avatar" alt="" />
											</p>
										</div>
										<br />
										<p class="am-text-lg">
											建材商名称：<a
												href="<%=path %>/BHsup/sup?id=${requestScope.sup.id }">${requestScope.sup.name }</a>
										</p>
										<p class="am-text-lg">商品名称：${requestScope.pro.name }</p>
										<p class="am-text-lg">商品原价：${requestScope.pro.price }元</p>
										<p class="am-text-lg">商品售价：${requestScope.pro.sale_price }元</p>
										<hr>
										<blockquote class="font-style">
											<dl>
												<dt>商品描述</dt>
												<dd>${requestScope.pro.des }</dd>
											</dl>
											<small>${requestScope.sup.name }</small>
										</blockquote>
									</div>
								</section>

								<hr>

								<div class="am-cf am-padding">
									<div class="am-fl am-cf">
										<strong class="am-text-primary am-text-xl">建材活动精选</strong>
									</div>
								</div>

								<div class="am-g">
									<c:forEach items="${requestScope.actTopList }" var="topAct"
										end="2" varStatus="status">
										<div class="am-u-sm-4">
											<div class="am-thumbnail" id="product1">
												<a href="<%=path %>/BHsup/supAct?id=${topAct.id }"><img
													src="<%=path %>/${topAct.image }" alt=""
													class="img-rounded" /></a>
												<div class="am-thumbnail-caption">
													<h4>活动名称：${topAct.name }</h4>
													<p class="am-text-truncate">简介：${topAct.des }</p>
													<p>
														<a href="<%=path %>/BHsup/supAct?id=${topAct.id }"
															type="button"
															class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
													</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
								<a href="<%=path %>/BHsup/supActMore?id=${requestScope.sup.id }"
									type="button" class="am-btn am-btn-secondary am-round am-fr">更多活动</a>
								<hr>

								<div class="am-cf am-padding">
									<div class="am-fl am-cf">
										<strong class="am-text-primary am-text-xl">建材商品精选</strong>
									</div>
								</div>

								<div class="am-g">
									<c:forEach items="${requestScope.proTopList }" var="pro"
										end="2" varStatus="status">
										<div class="am-u-sm-4">
											<div class="am-thumbnail" id="product2">
												<a href="<%=path %>/BHsup/pro?id=${pro.id }"><img
													src="<%=path %>/${pro.image }" alt="" class="img-rounded" /></a>
												<div class="am-thumbnail-caption">
													<h4>商品名称：${pro.name }</h4>
													<p class="am-text-truncate">仅售：${pro.sale_price }元</p>
													<p>
														<a href="<%=path %>/BHsup/pro?id=${pro.id }" type="button"
															class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
													</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
								<a href="<%=path %>/BHsup/proMore?id=${requestScope.sup.id }"
									type="button" class="am-btn am-btn-secondary am-round am-fr">更多商品</a>
								<hr>

								<footer class="admin-content-footer">
									<hr>
									<p class="am-padding-left am-text-lg">© 2017
										BeautHome-全国最大房屋装修平台 版权所有.</p>
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
	<script src="<%=path%>/js/amazeui.min.js"></script>
	<script src="<%=path%>/js/app.js"></script>
	<script src="<%=path%>/js/supply_save.js"></script>
	<script src="<%=path%>/js/cart.js"></script>
	<script src="<%=path%>/js/zoomify.min.js"></script>
	<script type="text/javascript">
		$('.example img').zoomify();
	</script>
</body>
</html>