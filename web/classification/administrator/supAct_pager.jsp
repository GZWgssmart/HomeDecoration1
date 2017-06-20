<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>所有建材商活动</title>
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
    <c:import url="/model/admin-left.jsp"></c:import>

    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>所有建材商活动</small></div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-lg-10 am-u-lg-offset-1">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd am-scrollable-horizontal">
                            <span id = "error">${requestScope.error }</span>
                            <table class="am-table am-table-bordered am-table-radius am-table-striped am-table-centered
                            am-text-nowrap">
                                <tr>
                                    <th>建材活动名称</th>
                                    <th>案例描述</th>
                                    <th>活动状态</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${requestScope.supActList }" var="supAct" varStatus="status">
	                                <tr>
	                                    <td>${supAct.name }</td>
	                                    <td>${supAct.des }</td>
	                                    <td>
	                                    	<c:if test="${supAct.status == 'Y' }">
		                                    	可用
		                                    </c:if>
		                                    <c:if test="${supAct.status == 'N' }">
		                                    	冻结
		                                    </c:if>
	                                    </td>
	                                    <td>${supAct.created_time }</td>
	                                    <td>
	                                        <a class="am-btn am-btn-primary" href="<%=path %>/BHsup/supAct?id=${supAct.id }">查看</a>
	                                        <c:if test="${supAct.status == 'Y' }">
	                                        	<a class="am-btn am-btn-primary" href="<%=path %>/admCon/supActstop?id=${supAct.id }">停用</a>
	                                    	</c:if>
	                                    	<c:if test="${supAct.status == 'N' }">
	                                        	<a class="am-btn am-btn-primary" href="<%=path %>/admCon/supActpass?id=${supAct.id }">启用</a>
	                                    	</c:if>
	                                    </td>
	                                </tr>
                                </c:forEach>
                            </table>
							<ul class="am-pagination am-pagination-centered">
                                <c:choose>
									<c:when test="${requestScope.pageNo == 1 }">
										<li><a href="#" class="am-btn am-disabled">&laquo;首页</a></li>
	                                	<li><a href="#" class="am-btn am-disabled">上一页</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=path %>/admCon/supActpager?pageNo=1" class="am-btn">&laquo;首页</a></li>
	                                	<li><a href="<%=path %>/admCon/supActpager?pageNo=${requestScope.pageNo - 1 }" class="am-btn">上一页</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${requestScope.pageNo == requestScope.pageCount || requestScope.pageNo > requestScope.pageCount }">
										<li><a href="#" class="am-btn am-disabled">下一页</a></li>
	                                	<li><a href="#" class="am-btn am-active am-disabled">尾页&raquo;</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=path %>/admCon/supActpager?pageNo=${requestScope.pageNo + 1 }" class="am-btn">下一页</a></li>
                                		<li><a href="<%=path %>/admCon/supActpager?pageNo=${requestScope.pageCount }" class="am-btn">尾页&raquo;</a></li>
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
</body>
</html>