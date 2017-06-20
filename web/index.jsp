<%@ page language="java" contentType="text/html; charset=UTF-8"
         errorPage="404.jsp" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>BeautHome-全国最大房屋装修平台</title>
    <link href="<%=path %>/css/amazeui.min.css" rel="stylesheet">
	<link href="<%=path %>/css/admin.css" rel="stylesheet">
	<link href="<%=path %>/css/animation.css" rel="stylesheet">
	<link href="<%=path %>/css/zzsc.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/index.css">
	
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

				<div class="am-slider am-slider-default" data-am-flexslider id="demo-slider-0">
					<ul class="am-slides">
						<li><a href="<%=path %>/BHcompany/case2?id=94ddc78e-4cf2-11e7-9e73-d017c205bc97"><img src="<%=path %>/img/score/2Q.jpg" /></a></li>
						<li><a href="<%=path %>/BHdes/des?id=85da255f-4a90-11e7-844f-d017c205bc97"><img src="<%=path %>/img/score/2QS.jpg" /></a></li>
						<li><a href="<%=path %>/BHdes/desCase?id=26e5bf90-4cf5-11e7-9e73-d017c205bc97"><img src="<%=path %>/img/score/9k.jpg" /></a></li>
						<li><a href="<%=path %>/BHsup/supAct?id=d80e41ab-4be1-11e7-9e73-d017c205bc97"><img src="<%=path %>/img/score/Z.jpg" /></a></li>
					</ul>
				</div>

				<div class="major-list-outer">
					<ul class="major-list">
						<li class="major-item" id="jx"><a href="<%=path %>/BHsup/allPro"><span class="txt-hide front-face"></span><span class="back-face"></span></a></li>
						<li class="major-item" id="jsj"><a href="<%=path %>/BHdes/all_des"><span class="txt-hide front-face"></span><span class="back-face"></span></a></li>
						<li class="major-item" id="jg"><a href="<%=path %>/BHcompany/company"><span class="txt-hide front-face"></span><span class="back-face"></span></a></li>
						<li class="major-item" id="wl"><a href="<%=path %>/BHsup/all_sup"><span class="txt-hide front-face"></span><span class="back-face"></span></a></li>
					</ul>
				</div>

							<div class="admin-content">
								<div class="admin-content-body">
									<div class="am-cf am-padding am-padding-bottom-0">
										<div class="am-fl am-cf">
											<strong class="am-text-primary am-text-xl">本站推荐</strong>
										</div>
									</div>

									<hr>

									<div class="am-tabs am-margin" data-am-tabs>
										<ul class="am-tabs-nav am-nav am-nav-tabs">
											<li class="am-active"><a href="#tab1">优秀设计师</a></li>
											<li><a href="#tab2">大型装修公司</a></li>
											<li><a href="#tab3">热卖商品</a></li>
										</ul>

										<div class="am-tabs-bd">
											<div class="am-tab-panel am-fade am-in am-active" id="tab1">
												<div class="am-g">
													<c:forEach items="${requestScope.desTopList }" var="topDes" end="2" varStatus="status">
					                               		<div class="am-u-sm-4">
					                                        <div class="am-thumbnail">
						                                            <a href="<%=path %>/BHdes/des?id=${topDes.id }"><img src="<%=path %>/${topDes.headicon }" alt="" class="img-rounded"/></a>
					                                            <div class="am-thumbnail-caption">
					                                                <h4 class="am-text-center">设计师名称：${topDes.name }</h4>
					                                                <p class="am-text-truncate am-text-center">简介：${topDes.des }</p>
					                                                <p>
					                                                    <a href="<%=path %>/BHdes/des?id=${topDes.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看详情</a>
					                                                </p>
					                                            </div>
					                                        </div>
					                                    </div>
				                                    </c:forEach>
												</div>
												<a href="<%=path %>/BHdes/all_des" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看全部</a>
											</div>

											<div class="am-tab-panel am-fade" id="tab2">
												<div class="am-g">
													<c:forEach items="${requestScope.companys }" var="comp" end="2" varStatus="status">
						                                <div class="am-u-sm-4">
						                                    <div class="am-thumbnail">
						                                        <a href="<%=path %>/BHcompany/comp?id=${comp.id }"><img src="<%=path %>/${comp.logo }" alt="" class="img-rounded"/></a>
						                                        <div class="am-thumbnail-caption">
						                                            <h4>公司名称：${comp.name }</h4>
						                                            <p class="am-text-truncate">简介：${comp.des }</p>
						                                            <p>
						                                                <a href="<%=path %>/BHcompany/comp?id=${comp.id }" class="am-btn am-btn-secondary am-btn-block am-round">查看详情</a>
						                                            </p>
						                                        </div>
						                                    </div>
						                                </div>
						                               </c:forEach>
												</div>
												<a href="<%=path %>/BHcompany/company" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看全部</a>
											</div>

											<div class="am-tab-panel am-fade" id="tab3">
												<div class="am-g">
													<c:forEach items="${requestScope.proTopList }" var="pro" end="2" varStatus="status">
						                                <div class="am-u-sm-4">
						                                    <div class="am-thumbnail">
						                                        <a href="<%=path %>/BHsup/pro?id=${pro.id }"><img src="<%=path %>/${pro.image }" alt="" class="img-rounded"/></a>
						                                        <div class="am-thumbnail-caption">
						                                            <h4>商品名称：${pro.name }</h4>
						                                            <p class="am-text-truncate">仅售：${pro.sale_price }元</p>
						                                            <p>
						                                                <a href="<%=path %>/BHsup/pro?id=${pro.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看详情</a>
						                                            </p>
						                                        </div>
						                                    </div>
						                                </div>
				                                	</c:forEach>
												</div>
												<a href="<%=path %>/BHsup/allPro" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看全部</a>
											</div>

										</div>
									</div>

								</div>
							</div>
							<hr>
							<div class="am-fl am-cf">
								<strong class="am-text-primary am-text-xl">精选公司案例</strong> <a href="<%=path %>/BHcompany/allcase" class="am-link-muted">所有案例</a>
							</div>
							<div class="wapper">
								<ul>
									<c:forEach items="${requestScope.companycases }" var="comCase" varStatus="status">
										<li>
											<div class="pic"><a href="<%=path %>/BHcompany/case1?id=${comCase.id}"><img src="<%=path %>/${comCase.image_2 }" width="270" height="270" /></a></div>
											<h4 class="title">案例名称：<a href="<%=path %>/BHcompany/case1?id=${comCase.id}">${comCase.name}</a></h4>
											<p class="desc am-text-truncate">案例介绍：${comCase.des}</p>
											<p class="price"> <span class="num">金牌案例</span> </p>
										</li>
									</c:forEach>
								</ul>
							</div>

							<hr>
							<div class="am-fl am-cf">
								<strong class="am-text-primary am-text-xl">精选设计师案例</strong> <a href="<%=path %>/BHdes/allCase" class="am-link-muted">所有案例</a>
							</div>
							<div class="wapper">
								<ul>
									<c:forEach items="${requestScope.desCaseTopList }" var="desCase" varStatus="status">
										<li>
											<div class="pic"><a href="<%=path %>/BHdes/desCase?id=${desCase.id }"><img src="<%=path %>/${desCase.image_2 }" width="270" height="270" /></a></div>
											<h4 class="title">案例名称<a href="<%=path %>/BHdes/desCase?id=${desCase.id }">${desCase.name}</a></h4>
											<p class="desc am-text-truncate">案例介绍：${desCase.des}</p>
											<p class="price"> <span class="num">金牌案例</span> </p>
										</li>
									</c:forEach>
								</ul>
							</div>

							<hr>
							<div class="am-fl am-cf">
								<strong class="am-text-primary am-text-xl">建材活动</strong> <a href="<%=path %>/BHsup/allAct" class="am-link-muted">更多活动</a>
							</div>
							<div class="wapper">
								<ul>
									<c:forEach items="${requestScope.actTopList }" var="actList" varStatus="status">
										<li>
											<div class="pic"><a href="<%=path %>/BHsup/supAct?id=${actList.id }"><img src="<%=path %>/${actList.image }" width="270" height="270" /></a></div>
											<h4 class="title">活动名称：<a href="<%=path %>/BHsup/supAct?id=${actList.id }">${actList.name }</a></h4>
											<p class="desc am-text-truncate">活动介绍：${actList.des }</p>
											<p class="price">火热进行中</p>
										</li>
									</c:forEach>
								</ul>
							</div>
							<hr>
							<div class="am-fl am-cf">
								<strong class="am-text-primary am-text-xl">装修公司活动</strong>
							</div>
							<div id="in_ct">
								<!----------in_ct开始---------->
								<div class="in_ct">
									<!----------in_ct1 开始---------->
									<div class="in_ct1">
										<ul class="box">
											<c:forEach items="${requestScope.companyActives }" var="actList" varStatus="status">
												<li>
													<div class="box1">
														<div class="toll_img"><a href="<%=path %>/BHcompany/active?id=${actList.id}"><img src="<%=path %>/${actList.image }" width="272" height="514"/></a></div>
													</div>
												</li>
											</c:forEach>
										</ul>
									</div>
									<!----------in_ct1 结束---------->
								</div>
								<!----------in_ct结束---------->
							</div>
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

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="<%=path %>/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="<%=path %>/js/amazeui.min.js"></script>
<script src="<%=path %>/js/app.js"></script>
<script src="<%=path %>/js/public.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.box .box1').mouseover(function() {
			$(this).stop().animate({
				"top" : "-514px"
			}, 200);
		});
		$('.box .box1').mouseout(function() {
			$(this).stop().animate({
				"top" : "0"
			}, 200);
		});
	});
</script>

</body>
</html>