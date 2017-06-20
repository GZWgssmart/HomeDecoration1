<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>

<link rel="stylesheet" href="<%=path %>/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=path %>/css/xcConfirm.css">
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/xcConfirm.js"></script>
<script type="text/javascript" src="<%=path%>/js/search.js"></script>
<header class="am-topbar am-topbar-inverse admin-header am-g font-style">

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">引导</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-left admin-header-list">
            <li class="am-dropdown" data-am-dropdown>
            <li><a href="<%=path %>/index/home" class="am-dropdown-toggle am-text-lg"><strong> BeautHome </strong></a></li>
            <li><a href="<%=path %>/index/home" class="am-dropdown-toggle am-text-lg"><span class="am-icon-home am-icon-sm"></span> 首页 </a></li>
            <li><a href="<%=path %>/BHcompany/company" class="am-dropdown-toggle am-text-lg"><span class="icon-group"></span> 装修公司 </a></li>
            <li><a href="<%=path %>/BHsup/all_sup" class="am-dropdown-toggle am-text-lg"><span class="icon-truck"></span> 建材商 </a></li>
            <li><a href="<%=path %>/BHdes/all_des" class="am-dropdown-toggle am-text-lg"><span class="icon-briefcase"></span> 设计师 </a></li>
        	<li>
				<select id="selectid" style="margin-top: 10px;">
				  <option onclick="getCreat();" value="搜索类型">搜索类型</option>
				  <option onclick="getCreat();"  value="装修公司">装修公司</option>
				  <option onclick="getCreat();"  value="建材商">建材商</option>
				  <option onclick="getCreat();"  value="设计师">设计师</option>
				  <option onclick="getCreat();" value="建材商品">建材商品</option>
				</select>
			</li>
            <li><input value="${requestScope.search}" style="width:250px;height:30px;margin-top: 10px;" id="search"  name="search" type="text" oninput="getCreat();" onfocus="getCreat();"/></li>
        	<li><input id="searchbtn" type="button" style="margin-top: 10px;" value="搜索" onclick="getCreatbtn()"/></li>
        </ul>
         <div id="popDiv"  style="background-color: #FFFAFA">
			<table id="content_table" style="position:absolute; left: 640px;top: 40px; width: 249px;">
			<tbody id="content_table_body">
			</tbody>
			</table>
		</div>

        <div class="am-fr">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle am-text-lg am-text-right" data-am-dropdown-toggle href="javascript:;">
                    <c:if test="${sessionScope.customer.id != null }">
                   		${sessionScope.customer.name }，您好！
                    </c:if>
                    <c:if test="${sessionScope.designer.id != null }">
                        ${sessionScope.designer.name }，您好！
                    </c:if>
                    <c:if test="${sessionScope.supply.id != null }">
                        ${sessionScope.supply.name }，您好！
                    </c:if>
                    <c:if test="${sessionScope.admin.id != null }">
                        ${sessionScope.admin.name }，您好！
                    </c:if>
                    <c:if test="${sessionScope.company.id != null }">
                        ${sessionScope.company.name }，您好！
                    </c:if>
                </a>
                <ul class="am-dropdown-content">
                    <li>
                        <c:if test="${sessionScope.customer.id != null }">
                            <a href="<%=path %>/cus/home">个人中心</a>
                        </c:if>
                        <c:if test="${sessionScope.designer.id != null }">
                            <a href="<%=path %>/des/home">个人中心</a>
                        </c:if>
                        <c:if test="${sessionScope.supply.id != null }">
                            <a href="<%=path %>/supply/home">个人中心</a>
                        </c:if>
                        <c:if test="${sessionScope.admin.id != null }">
                            <a href="<%=path %>/adm/home">个人中心</a>
                        </c:if>
                        <c:if test="${sessionScope.company.id != null }">
                            <a href="<%=path %>/company/home">个人中心</a>
                        </c:if>
                    </li>
                    <li>
                        <c:if test="${sessionScope.customer.id != null }">
                            <a href="<%=path %>/cus/exit">注销</a>
                        </c:if>
                        <c:if test="${sessionScope.designer.id != null }">
                            <a href="<%=path %>/des/exit">注销</a>
                        </c:if>
                        <c:if test="${sessionScope.supply.id != null }">
                            <a href="<%=path %>/supply/exit">注销</a>
                        </c:if>
                        <c:if test="${sessionScope.admin.id != null }">
                            <a href="<%=path %>/adm/exit">注销</a>
                        </c:if>
                        <c:if test="${sessionScope.company.id != null }">
                            <a href="<%=path %>/company/exit">注销</a>
                        </c:if>
                    </li>
                </ul>
            </li>
            <c:if test="${sessionScope.customer.id != null }">
            	<li><a href="<%=path %>/cart/cart">购物车</a></li>
            </c:if>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"> <span class="admin-fullText am-text-lg"> 开启全屏</span></a></li>
        </ul>
        </div>
    </div>
</header>