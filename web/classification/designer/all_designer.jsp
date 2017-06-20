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
<title>设计师展示</title>
<link href="<%=path %>/css/amazeui.min.css" rel="stylesheet">
<link href="<%=path %>/css/admin.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path %>/css/bootstrap-grid.min.css">
<link rel="stylesheet" href="<%=path %>/css/zoomify.min.css">
<link rel="stylesheet" href="<%=path %>/css/style.css">
<link rel="stylesheet" href="<%=path %>/css/zzsc.css">
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
							<div class="am-panel-bd">
								<div class="am-cf am-padding">
									<div class="am-fl am-cf">
										<strong class="am-text-primary am-text-lg"><a
											href="javascript:history.go(-1)">返回</a></strong> / <small>设计师</small>
									</div>
								</div>
								<hr>
								<div class="am-cf am-padding">
									<h1 class="font-style am-text-center">所有设计师</h1>
								</div>
								<hr>

								<div class="am-cf am-padding">
									<div class="am-fl am-cf">
										<strong class="am-text-primary am-text-xl">全站优选精英设计师TOP5</strong>
									</div>
								</div>
								<div id="ztbox">
									<div id="left"></div>
									<div id="conter" class="am-center">
										<ul>
											<c:forEach items="${requestScope.desTopList }"
												varStatus="status" var="top">
												<li>
													<h3 class="am-text-center am-text-danger">NO.${status.count }</h3>
													<a href="<%=path %>/BHdes/des?id=${top.id }"><img
														src="<%=path %>/${top.headicon }" /></a>
												</li>
											</c:forEach>
										</ul>
										<div id="scroll">
											<span></span>
										</div>
									</div>
									<div id="right"></div>
								</div>
								<hr>
								<div class="am-cf am-padding">
									<div class="am-fl am-cf">
										<strong class="am-text-primary am-text-xl">每月热门设计师</strong>
									</div>
								</div>
								<div class="am-g">
									<c:forEach items="${requestScope.desTopList }" var="topDes"
										end="2" varStatus="status">
										<div class="am-u-sm-4">
											<div class="am-thumbnail" id="designers">
												<a href="<%=path %>/BHdes/des?id=${topDes.id }"><img
													src="<%=path %>/${topDes.headicon }" alt=""
													class="img-rounded" /></a>
												<div class="am-thumbnail-caption">
													<h4 class="am-text-center">昵称：${topDes.name }</h4>
													<p class="am-text-truncate am-text-center">简介：${topDes.des }</p>
													<p>
														<a href="<%=path %>/BHdes/des?id=${topDes.id }"
															type="button"
															class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
													</p>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<hr>

							<div class="am-cf am-padding">
								<div class="am-fl am-cf">
									<strong class="am-text-primary am-text-xl">设计师案例精选</strong>
								</div>
							</div>

							<div class="am-g">
								<c:forEach items="${requestScope.desCaseTopList }" var="topCase"
									end="2" varStatus="status">
									<div class="am-u-sm-4">
										<div class="am-thumbnail" id="des">
											<a href="<%=path %>/BHdes/desCase?id=${topCase.id }"><img
												src="<%=path %>/${topCase.image_1 }" alt=""
												class="img-rounded" /></a>
											<div class="am-thumbnail-caption">
												<h4>案例名称：${topCase.name }</h4>
												<p class="am-text-truncate">简介：${topCase.des }</p>
												<p>
													<a href="<%=path %>/BHdes/desCase?id=${topCase.id }"
														type="button"
														class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
												</p>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
							<a href="<%=path %>/BHdes/allCase" type="button"
								class="am-btn am-btn-secondary am-round am-fr">所有案例</a>
							<hr>

							<div class="admin-content">
								<div class="admin-content-body">
									<div class="am-cf am-padding am-padding-bottom-0">
										<div class="am-fl am-cf">
											<strong class="am-text-primary am-text-lg">所有设计师</strong>
										</div>
									</div>

									<hr>

									<div class="am-tabs am-margin" data-am-tabs>
										<ul class="am-tabs-nav am-nav am-nav-tabs">
											<li class="am-active"><a href="#tab1">所有设计师</a></li>
										</ul>

										<div class="am-tabs-bd">
											<div class="am-tab-panel am-fade am-in am-active" id="tab1">
												<span id="error">${requestScope.error }</span>
												<table class="am-table am-table-bordered am-table-radius">
													<thead>
														<tr class="am-g">
															<th class="am-u-sm-3 am-text-center">设计师名称</th>
															<th class="am-u-sm-3 am-text-center">擅长风格</th>
															<th class="am-u-sm-3 am-text-center">工作经验</th>
															<th class="am-u-sm-3 am-text-center">操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${requestScope.desList }" var="des"
															varStatus="status">
															<tr class="am-g">
																<td class="am-u-sm-3 am-text-center">${des.name }</td>
																<td class="am-u-sm-3 am-text-center">${des.style }</td>
																<td class="am-u-sm-3 am-text-center"><c:if
																		test="${des.experience == 'workexp1' }">
																		<p class="am-text-lg">工作经验：1~3年</p>
																	</c:if> <c:if test="${des.experience == 'workexp2' }">
																		<p class="am-text-lg">工作经验：3~5年</p>
																	</c:if> <c:if test="${des.experience == 'workexp3' }">
																		<p class="am-text-lg">工作经验：5~8年</p>
																	</c:if> <c:if test="${des.experience == 'workexp4' }">
																		<p class="am-text-lg">工作经验：8年以上</p>
																	</c:if> <c:if test="${des.experience == null }">
																		<p class="am-text-lg">工作经验：无</p>
																	</c:if></td>
																<td
																	class="am-u-sm-3 am-text-center am-btn-group am-btn-group-xs">
																	<div>
																		<div class="am-btn-group">
																			<input type="hidden" id="id" value="${des.id }" /> <input
																				type="hidden" id="cus_id"
																				value="${sessionScope.customer.id }" /> <a
																				href="<%=path %>/BHdes/des?id=${des.id }"
																				type="button"
																				class="am-btn am-btn-default am-btn-xs am-text-secondary am-text-center am-center"><span
																				class="am-icon-pencil-square-o"></span> 查看</a>
																		</div>
																	</div>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>

												<ul class="am-pagination am-pagination-centered">
													<c:choose>
														<c:when test="${requestScope.pageNo == 1 }">
															<li><a href="#" class="am-btn am-disabled">&laquo;首页</a></li>
															<li><a href="#" class="am-btn am-disabled">上一页</a></li>
														</c:when>
														<c:otherwise>
															<li><a href="<%=path %>/BHdes/all_des?pageNo=1"
																class="am-btn">&laquo;首页</a></li>
															<li><a
																href="<%=path %>/BHdes/all_des?pageNo=${requestScope.pageNo -1 }"
																class="am-btn">上一页</a></li>
														</c:otherwise>
													</c:choose>
													<c:choose>
														<c:when
															test="${requestScope.pageNo == requestScope.pageCount || requestScope.pageNo > requestScope.pageCount }">
															<li><a href="#" class="am-btn am-disabled">下一页</a></li>
															<li><a href="#" class="am-btn am-active am-disabled">尾页&raquo;</a></li>
														</c:when>
														<c:otherwise>
															<li><a
																href="<%=path %>/BHdes/all_des?pageNo=${requestScope.pageNo + 1 }"
																class="am-btn">下一页</a></li>
															<li><a
																href="<%=path %>/BHdes/all_des?pageNo=${requestScope.pageCount }"
																class="am-btn">尾页&raquo;</a></li>
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
