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
    <title>案例展示</title>
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
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>建材商</small></div>
                            </div>
                            <hr>
                            <div>
                                <p class="am-text-xxl am-text-center font-style">所有建材商</p>
                            </div>
                            <hr>
                            <div class="am-cf am-padding">
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-xl">每月诚信建材商</strong></div>
                            </div>
                            <div class="am-g">
                                <c:forEach items="${requestScope.supTopList }" var="topSup" end="2" varStatus="status">
                               		<div class="am-u-sm-4">
                                        <div class="am-thumbnail" id="topsup1">
	                                            <a href="<%=path %>/BHsup/sup?id=${topSup.id }"><img src="<%=path %>/${topSup.logo }" alt="" class="img-rounded"/></a>
                                            <div class="am-thumbnail-caption">
                                                <h4 class="am-text-center">建材商名称：${topSup.name }</h4>
                                                <p class="am-text-truncate am-text-center">简介：${topSup.des }</p>
                                                <p>
                                                    <a href="<%=path %>/BHsup/sup?id=${topSup.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                   </c:forEach>
                            </div>
                            <hr>
                            <div class="am-g">
                                <c:forEach items="${requestScope.supTopList }" var="topSup" begin="3" end="5" varStatus="status">
                              		<div class="am-u-sm-4">
                                       <div class="am-thumbnail" id="topsup2">
                                            <a href="<%=path %>/BHsup/sup?id=${topSup.id }"><img src="<%=path %>/${topSup.logo }" alt="" class="img-rounded"/></a>
                                           <div class="am-thumbnail-caption">
                                               <h4 class="am-text-center">建材商名称：${topSup.name }</h4>
                                               <p class="am-text-truncate am-text-center">简介：${topSup.des }</p>
                                               <p>
                                                   <a href="<%=path %>/BHsup/sup?id=${topSup.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
                                               </p>
                                           </div>
                                       </div>
                                   </div>
                                  </c:forEach>
                            </div>

                            <hr>

                            <div class="am-cf am-padding">
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-xl">建材商热门活动</strong></div>
                            </div>
                            <div class="am-g">
                                <c:forEach items="${requestScope.actTopList }" var="topAct" end="2" varStatus="status">
	                                <div class="am-u-sm-4">
	                                    <div class="am-thumbnail" id="topsup3">
	                                        <a href="<%=path %>/BHsup/supAct?id=${topAct.id }"><img src="<%=path %>/${topAct.image }" alt="" class="img-rounded"/></a>
	                                        <div class="am-thumbnail-caption">
	                                            <h4>活动名称：${topAct.name }</h4>
	                                            <p class="am-text-truncate">简介：${topAct.des }</p>
	                                            <p>
	                                                <a href="<%=path %>/BHsup/supAct?id=${topAct.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
	                                            </p>
	                                        </div>
	                                    </div>
	                                </div>
                                </c:forEach>
                            </div>
                            <a href="<%=path %>/BHsup/allAct" type="button" class="am-btn am-btn-secondary am-round am-fr">所有活动</a>
                            <hr>
                            
                            
                            <div class="am-cf am-padding">
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-xl">热卖建材</strong></div>
                            </div>
                            <div class="am-g">
                                <c:forEach items="${requestScope.proTopList }" var="topPro" end="2" varStatus="status">
	                                <div class="am-u-sm-4">
	                                    <div class="am-thumbnail" id="topsup4">
	                                        <a href="<%=path %>/BHsup/pro?id=${topPro.id }"><img src="<%=path %>/${topPro.image }" alt="" class="img-rounded"/></a>
	                                        <div class="am-thumbnail-caption">
	                                            <h4>商品名称：${topPro.name }</h4>
	                                            <p class="am-text-truncate">仅售：${topPro.sale_price }元</p>
	                                            <p>
	                                                <a href="<%=path %>/BHsup/pro?id=${topPro.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
	                                            </p>
	                                        </div>
	                                    </div>
	                                </div>
                                </c:forEach>
                            </div>
                            <a href="<%=path %>/BHsup/allPro" type="button" class="am-btn am-btn-secondary am-round am-fr">所有建材</a>
                            <hr>

                            <div class="admin-content">
                                <div class="admin-content-body">
                                    <div class="am-cf am-padding am-padding-bottom-0">
                                        <div class="am-fl am-cf">
                                            <strong class="am-text-primary am-text-lg">所有商人</strong>
                                        </div>
                                    </div>

                                    <hr>

                                    <div class="am-tabs am-margin" data-am-tabs>
                                        <ul class="am-tabs-nav am-nav am-nav-tabs">
                                            <li class="am-active"><a href="#tab1">所有建材商</a></li>
                                        </ul>

                                        <div class="am-tabs-bd">
                                            <div class="am-tab-panel am-fade am-in am-active am-scrollable-horizontal" id="tab1">
                                                <table class="am-table am-table-bordered am-table-radius am-text-nowrap">
                                                    <thead>
                                                    <tr class="am-g">
                                                        <th class="am-u-sm-3 am-text-center">建材商名称</th>
                                                        <th class="am-u-sm-3 am-text-center">地址</th>
                                                        <th class="am-u-sm-3 am-text-center">负责人</th>
                                                        <th class="am-u-sm-3 am-text-center">操作</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${requestScope.supList }" var="sup" varStatus="status">
	                                                    <tr class="am-g">
	                                                        <td class="am-u-sm-3 am-text-center">${sup.name }</td>
	                                                        <td class="am-u-sm-3 am-text-center">${sup.address }</td>
	                                                        <td class="am-u-sm-3 am-text-center">${sup.principal }</td>
	                                                        <td class="am-u-sm-3 am-text-center am-btn-group am-btn-group-xs">
	                                                            <div>
	                                                                <div class="am-btn-group">
		                                                                <input type="hidden" id="id" value="${sup.id }" />
		                            									<input type="hidden" id="cus_id" value="${sessionScope.customer.id }" />
	                                                                    <a href="<%=path %>/BHsup/sup?id=${sup.id }" type="button" class="am-btn am-btn-default am-btn-xs am-text-secondary am-text-center am-center"><span class="am-icon-pencil-square-o"></span> 查看</a>
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
										<li><a  href="<%=path %>/BHsup/all_sup?pageNo=1" class="am-btn">&laquo;首页</a></li>
	                                	<li><a href="<%=path %>/BHsup/all_sup?pageNo=${requestScope.pageNo -1 }" class="am-btn">上一页</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${requestScope.pageNo == requestScope.pageCount || requestScope.pageNo > requestScope.pageCount }">
										<li><a href="#" class="am-btn am-disabled">下一页</a></li>
	                                	<li><a href="#" class="am-btn am-active am-disabled">尾页&raquo;</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=path %>/BHsup/all_sup?pageNo=${requestScope.pageNo + 1 }" class="am-btn">下一页</a></li>
                                		<li><a href="<%=path %>/BHsup/all_sup?pageNo=${requestScope.pageCount }" class="am-btn">尾页&raquo;</a></li>
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
