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
    <title>建材商的所有活动展示</title>
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
                                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>建材活动</small></div>
                            </div>
                            <hr>
                            <div>
                                <p class="am-text-xxl am-text-center font-style"><a href="<%=path %>/BHsup/sup?id=${requestScope.sup.id }">${requestScope.sup.name }</a>的所有活动</p>
                            </div>
                            <hr>
                            <div>
                            <div class="am-g">
                                <c:forEach items="${requestScope.supActList }" var="supAct" end="2" varStatus="status">
		                                <div class="am-u-sm-4">
		                                    <div class="am-thumbnail" id="supAct1">
		                                        <a href="<%=path %>/BHsup/supAct?id=${supAct.id }"><img src="<%=path %>/${supAct.image }" alt="" class="img-rounded"/></a>
		                                        <div class="am-thumbnail-caption">
		                                            <h4>活动名称：${supAct.name }</h4>
		                                            <p class="am-text-truncate">${supAct.des }</p>
		                                            <p>
		                                                <a href="<%=path %>/BHsup/supAct?id=${supAct.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
		                                            </p>
		                                        </div>
		                                    </div>
		                                </div>
                                	</c:forEach>
                            </div>
                            <hr>
                            <div class="am-g"  id="supAct2">
                                <c:forEach items="${requestScope.supActList }" var="supAct" begin="3" end="5" varStatus="status">
		                                <div class="am-u-sm-4">
		                                    <div class="am-thumbnail">
		                                        <a href="<%=path %>/BHsup/supAct?id=${supAct.id }"><img src="<%=path %>/${supAct.image }" alt="" class="img-rounded"/></a>
		                                        <div class="am-thumbnail-caption">
		                                            <h4>活动名称：${supAct.name }</h4>
		                                            <p class="am-text-truncate">${supAct.des }</p>
		                                            <p>
		                                                <a href="<%=path %>/BHsup/supAct?id=${supAct.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
		                                            </p>
		                                        </div>
		                                    </div>
		                                </div>
                                	</c:forEach>
                            </div>

                            <hr>

                            <div class="am-g"  id="supAct3">
                                <c:forEach items="${requestScope.supActList }" var="supAct" begin="6" end="8" varStatus="status">
		                                <div class="am-u-sm-4">
		                                    <div class="am-thumbnail">
		                                        <a href="<%=path %>/BHsup/supAct?id=${supAct.id }"><img src="<%=path %>/${supAct.image }" alt="" class="img-rounded"/></a>
		                                        <div class="am-thumbnail-caption">
		                                            <h4>活动名称：${supAct.name }</h4>
		                                            <p class="am-text-truncate">${supAct.des }</p>
		                                            <p>
		                                                <a href="<%=path %>/BHsup/supAct?id=${supAct.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
		                                            </p>
		                                        </div>
		                                    </div>
		                                </div>
                                	</c:forEach>
                            </div>
                            <hr>

                                    <div class="am-g"  id="supAct4">
                                        <c:forEach items="${requestScope.supActList }" var="supAct" begin="9" end="11" varStatus="status">
		                                <div class="am-u-sm-4">
		                                    <div class="am-thumbnail">
		                                        <a href="<%=path %>/BHsup/supAct?id=${supAct.id }"><img src="<%=path %>/${supAct.image }" alt="" class="img-rounded"/></a>
		                                        <div class="am-thumbnail-caption">
		                                            <h4>活动名称：${supAct.name }</h4>
		                                            <p class="am-text-truncate">${supAct.des }</p>
		                                            <p>
		                                                <a href="<%=path %>/BHsup/supAct?id=${supAct.id }" type="button" class="am-btn am-btn-secondary am-btn-block am-round">查看</a>
		                                            </p>
		                                        </div>
		                                    </div>
		                                </div>
                                	</c:forEach>
                                    </div>
                                <ul class="am-pagination am-pagination-centered">
                                <c:choose>
									<c:when test="${requestScope.pageNo == 1 }">
										<li><a href="#" class="am-btn am-disabled">&laquo;首页</a></li>
	                                	<li><a href="#" class="am-btn am-disabled">上一页</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=path %>/BHsup/supActMore?pageNo=1&id=${requestScope.sup.id}" class="am-btn">&laquo;首页</a></li>
	                                	<li><a href="<%=path %>/BHsup/supActMore?pageNo=${requestScope.pageNo - 1 }&id=${requestScope.sup.id }" class="am-btn">上一页</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${requestScope.pageNo == requestScope.pageCount || requestScope.pageNo > requestScope.pageCount }">
										<li><a href="#" class="am-btn am-disabled">下一页</a></li>
	                                	<li><a href="#" class="am-btn am-active am-disabled">尾页&raquo;</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=path %>/BHsup/supActMore?pageNo=${requestScope.pageNo + 1 }&id=${requestScope.sup.id }" class="am-btn">下一页</a></li>
                                		<li><a href="<%=path %>/BHsup/supActMore?pageNo=${requestScope.pageCount }&id=${requestScope.sup.id }" class="am-btn">尾页&raquo;</a></li>
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
