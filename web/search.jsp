<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="404.jsp" pageEncoding="UTF-8"%>
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
<title>搜索</title>
<link href="<%=path %>/css/amazeui.min.css" rel="stylesheet">
<link href="<%=path %>/css/admin.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path %>/css/bootstrap-grid.min.css">
<link rel="stylesheet" href="<%=path %>/css/zoomify.min.css">
<link rel="stylesheet" href="<%=path %>/css/style.css">
<link rel="stylesheet" href="<%=path %>/css/font-awesome.min.css">
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
							<c:if test="${requestScope.com !=null }">
								<div class="am-panel-bd">
									<div class="am-cf am-padding">
										<div class="am-fl am-cf">
											<strong class="am-text-primary am-text-lg"><a
												href="javascript:history.go(-1)">返回</a></strong> / <small>装修公司</small>
										</div>
										<br />
										<div class="am-fl am-cf">当前搜索"${requestScope.search}"
											,共找到${requestScope.comtotal}个。</div>
									</div>
									<hr>
									<div>
										<p class="am-text-xxl am-text-center font-style">所有装修公司</p>
									</div>
									<br>
									<div>
										<div class="am-g">
											<c:forEach items="${requestScope.com }" var="comp"
												varStatus="status">
												<div class="am-u-sm-4">
													<div class="am-thumbnail" id="com2">
														<a href="<%=path %>/BHcompany/comp?id=${comp.id }"><img
															src="<%=path %>/${comp.logo }" alt="" class="img-rounded" /></a>
														<div class="am-thumbnail-caption">
															<h4>公司名称:${comp.name }</h4>
															<p class="am-text-truncate">公司描述:${comp.des }</p>
															<p>
																<a href="<%=path %>/BHcompany/comp?id=${comp.id }"
																	class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
															</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
									<div>
									<c:if test="${requestScope.comtotal > 6}">
									<ul class="am-pagination am-pagination-centered">
		                                <c:choose>
											<c:when test="${requestScope.compageNo == 1 }">
												<li><a href="#" class="am-btn am-disabled">&laquo;首页</a></li>
			                                	<li><a href="#" class="am-btn am-disabled">上一页</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="<%=path %>/search/combtn?pageNo=1" class="am-btn">&laquo;首页</a></li>
			                                	<li><a href="<%=path %>/search/combtn?search=${requestScope.search }&pageNo=${requestScope.compageNo -1 }" class="am-btn">上一页</a></li>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${requestScope.compageNo == requestScope.compageCount || requestScope.pageNo > requestScope.compageCount }">
												<li><a href="#" class="am-btn am-disabled">下一页</a></li>
			                                	<li><a href="#" class="am-btn am-active am-disabled">尾页&raquo;</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="<%=path %>/search/combtn?search=${requestScope.search }&pageNo=${requestScope.compageNo +1 }" class="am-btn">下一页</a></li>
		                                		<li><a href="<%=path %>/search/combtn?search=${requestScope.search }&pageNo=${requestScope.compageCount }" class="am-btn">尾页&raquo;</a></li>
											</c:otherwise>
										</c:choose>
		                                <li>${requestScope.compageNo }</li>
		                                <li>/</li>
		                                <li>${requestScope.compageCount }</li>
		                            </ul>
									</c:if>
									</div>
									<hr>
									<footer class="admin-content-footer">
										<p class="am-padding-left am-text-lg">© 2017
											BeautHome-全国最大房屋装修平台 版权所有.</p>
									</footer>
								</div>
							</c:if>

							<c:if test="${requestScope.sup !=null }">
								<div class="am-panel-bd">
									<div class="am-cf am-padding">
										<div class="am-fl am-cf">
											<strong class="am-text-primary am-text-lg"><a
												href="javascript:history.go(-1)">返回</a></strong> / <small>建材商</small>
										</div>
										<br />
										<div class="am-fl am-cf">当前搜索"${requestScope.search}"
											,共找到${requestScope.suptotal}个。</div>
									</div>
									<hr>
									<div>
										<p class="am-text-xxl am-text-center font-style">所有建材商</p>
									</div>
									<br>
									<div>
										<div class="am-g">
											<c:forEach items="${requestScope.sup }" var="sup"
												varStatus="status">
												<div class="am-u-sm-4">
													<div class="am-thumbnail" id="topsup1">
														<a href="<%=path %>/BHsup/sup?id=${sup.id }"><img
															src="<%=path %>/${sup.logo }" alt="" class="img-rounded" /></a>
														<div class="am-thumbnail-caption">
															<h4 class="am-text-center">建材商名称：${sup.name }</h4>
															<p class="am-text-truncate am-text-center">简介：${sup.des }</p>
															<p>
																<a href="<%=path %>/BHsup/sup?id=${sup.id }"
																	type="button"
																	class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
															</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
									<div>
									<c:if test="${requestScope.suptotal > 6}">
										<ul class="am-pagination am-pagination-centered">
											<li><a href="<%=path %>/BHsup/allPro?pageNo=1">&laquo;首页</a></li>
											<li class="am-active"><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.suppageNo -1 }">上一页</a></li>
											<li><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.suppageNo + 1 }">下一页</a></li>
											<li><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.suppageCount }">尾页&raquo;</a></li>
											<li>${requestScope.suppageNo }</li>
											<li>/</li>
											<li>${requestScope.suppageCount }</li>
										</ul>
									</c:if>
									</div>
									<hr>
									<footer class="admin-content-footer">
										<p class="am-padding-left am-text-lg">© 2017
											BeautHome-全国最大房屋装修平台 版权所有.</p>
									</footer>
								</div>
							</c:if>

							<c:if test="${requestScope.des !=null }">
								<div class="am-panel-bd">
									<div class="am-cf am-padding">
										<div class="am-fl am-cf">
											<strong class="am-text-primary am-text-lg"><a
												href="javascript:history.go(-1)">返回</a></strong> / <small>设计师</small>
										</div>
										<br />
										<div class="am-fl am-cf">当前搜索"${requestScope.search}"
											,共找到${requestScope.destotal}个。</div>
									</div>
									<hr>
									<div>
										<p class="am-text-xxl am-text-center font-style">所有设计师</p>
									</div>
									<br>
									<div>
										<div class="am-g">
											<c:forEach items="${requestScope.des }" var="des"
												varStatus="status">
												<div class="am-u-sm-4">
													<div class="am-thumbnail" id="designers">
														<a href="<%=path %>/BHdes/des?id=${des.id }"><img
															src="<%=path %>/${des.headicon }" alt=""
															class="img-rounded" /></a>
														<div class="am-thumbnail-caption">
															<h4 class="am-text-center">昵称：${des.name }</h4>
															<p class="am-text-truncate am-text-center">简介：${des.des }</p>
															<p>
																<a href="<%=path %>/BHdes/des?id=${des.id }"
																	type="button"
																	class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
															</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
									<div>
									<c:if test="${requestScope.destotal > 6}">
										<ul class="am-pagination am-pagination-centered">
											<li><a href="<%=path %>/BHsup/allPro?pageNo=1">&laquo;首页</a></li>
											<li class="am-active"><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.despageNo -1 }">上一页</a></li>
											<li><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.despageNo + 1 }">下一页</a></li>
											<li><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.despageCount }">尾页&raquo;</a></li>
											<li>${requestScope.despageNo }</li>
											<li>/</li>
											<li>${requestScope.despageCount }</li>
										</ul>
									</c:if>
									</div>
									<hr>
									<footer class="admin-content-footer">
										<p class="am-padding-left am-text-lg">© 2017
											BeautHome-全国最大房屋装修平台 版权所有.</p>
									</footer>
								</div>
							</c:if>

							<c:if test="${requestScope.pro !=null }">
								<div class="am-panel-bd">
									<div class="am-cf am-padding">
										<div class="am-fl am-cf">
											<strong class="am-text-primary am-text-lg"><a
												href="javascript:history.go(-1)">返回</a></strong> / <small>建材商品</small>
										</div>
										<br />
										<div class="am-fl am-cf">当前搜索"${requestScope.search}"
											,共找到${requestScope.prototal}个。</div>
									</div>
									<hr>
									<div>
										<p class="am-text-xxl am-text-center font-style">所有建材商品</p>
									</div>
									<br>
									<div>
										<div class="am-g">
												<c:forEach items="${requestScope.pro }" var="pro" end="2" varStatus="status">
		                                <div class="am-u-sm-4">
		                                    <div class="am-thumbnail" id="pro1">
		                                        <a href="<%=path %>/BHsup/pro?id=${pro.id }"><img src="<%=path %>/${pro.image }" alt="" class="img-rounded"/></a>
		                                        <div class="am-thumbnail-caption">
		                                            <h4>商品名称：${pro.name }</h4>
		                                            <p class="am-text-truncate">仅售：${pro.sale_price }元</p>
		                                            <p>
		                                                <a href="<%=path %>/BHsup/pro?id=${pro.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
		                                            </p>
		                                        </div>
		                                    </div>
		                                </div>
                                	</c:forEach>
										</div>
									</div>
									<div>
									<c:if test="${requestScope.prototal > 6}">
										<ul class="am-pagination am-pagination-centered">
											<li><a href="<%=path %>/BHsup/allPro?pageNo=1">&laquo;首页</a></li>
											<li class="am-active"><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.propageNo -1 }">上一页</a></li>
											<li><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.propageNo + 1 }">下一页</a></li>
											<li><a
												href="<%=path %>/BHsup/allPro?pageNo=${requestScope.prototalPage }">尾页&raquo;</a></li>
											<li>${requestScope.propageNo }</li>
											<li>/</li>
											<li>${requestScope.prototalPage }</li>
										</ul>
									</c:if>
									</div>
									<hr>
									<footer class="admin-content-footer">
										<p class="am-padding-left am-text-lg">© 2017
											BeautHome-全国最大房屋装修平台 版权所有. </p>
									</footer>
								</div>
							</c:if>
							
							<c:choose>
								<c:when test=""></c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
							
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
	<script src="<%=path %>/js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="<%=path %>/js/zoomify.min.js"></script>
	<script type="text/javascript">
    $('.example img').zoomify();
</script>
</body>
</html>
