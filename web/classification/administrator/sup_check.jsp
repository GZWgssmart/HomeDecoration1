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
    <title>建材商审核</title>
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
    <!-- sidebar start   am-text-nowrap 禁止换行 am-scrollable-horizontal 表格滚动条-->
    <c:import url="/model/admin-left.jsp"></c:import>

    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg"><a href="javascript:history.go(-1)" >返回</a></strong> / <small>建材商审核</small></div>
            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-lg-10 am-u-lg-offset-1">
                    <div class="am-panel am-panel-default">
                        <div class="am-panel-bd am-scrollable-horizontal">

                            <span id = "error">${requestScope.error }</span>
                            <table class="am-table am-table-bordered am-table-radius am-table-striped am-table-centered
                            am-text-nowrap">
                                <caption>建材商审核表</caption>
                                <tr>
                                    <th>建材商名称</th>
                                    <th>邮箱</th>
                                    <th>手机号</th>
                                    <th>负责人</th>
                                   	<th>审核状态</th>
                                    <th>申请时间</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach items="${requestScope.supList }" var="sup" varStatus="status">
	                                <tr>
	                                    <td>${sup.name }</td>
	                                    <td>${sup.email }</td>
	                                    <td>${sup.phone }</td>
	                                    <td>${sup.principal }</td>
	                                    <td>
	                                    	<c:if test="${sup.checked == 'Y' }">
		                                    	通过
		                                    </c:if>
		                                    <c:if test="${sup.checked == 'N' }">
		                                    	审核中
		                                    </c:if>
	                                    </td>
	                                    <td>${sup.created_time }</td>
	                                    <td><button type="button" class="am-btn am-btn-primary" data-am-modal="{target: '#my-popup'}">查看</button>
	                                        <div class="am-popup" id="my-popup">
	                                            <div class="am-popup-inner">
	                                                <div class="am-popup-hd">
	                                                    <h4 class="am-popup-title">详细信息</h4>
	                                                    <span data-am-modal-close
	                                                          class="am-close">&times;</span>
	                                                </div>
	                                                <ul class="am-list am-list-static am-g-fixed">
	                                                <li>建材商名称：${sup.name }</li>
	                                                <li>邮箱：${sup.email }</li>
	                                                <li>地址：${sup.address }</li>
	                                                <li>手机号：${sup.phone }</li>
	                                                <li>负责人：${sup.principal }</li>
	                                                <li>审核状态：
	                                                	<c:if test="${sup.checked == 'Y' }">
					                                    	通过
					                                    </c:if>
					                                    <c:if test="${sup.checked == 'N' }">
					                                    	审核中
					                                    </c:if>
	                                                </li>
	                                                <li>申请时间：${sup.created_time }</li>
	                                                </ul>
	                                            </div>
	                                        </div>
	                                        <a class="am-btn am-btn-primary" href="<%=path %>/adm/suppass?id=${sup.id }&email=${sup.email }">通过</a>
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
										<li><a href="<%=path %>/adm/sup_check?pageNo=1" class="am-btn">&laquo;首页</a></li>
	                                	<li><a href="<%=path %>/adm/sup_check?pageNo=${requestScope.pageNo - 1 }" class="am-btn">上一页</a></li>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${requestScope.pageNo == requestScope.pageCount || requestScope.pageNo > requestScope.pageCount }">
										<li><a href="#" class="am-btn am-disabled">下一页</a></li>
	                                	<li><a href="#" class="am-btn am-active am-disabled">尾页&raquo;</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="<%=path %>/adm/sup_check?pageNo=${requestScope.pageNo + 1 }" class="am-btn">下一页</a></li>
                                		<li><a href="<%=path %>/adm/sup_check?pageNo=${requestScope.pageCount }" class="am-btn">尾页&raquo;</a></li>
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