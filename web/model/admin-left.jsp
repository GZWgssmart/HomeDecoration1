<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>

	<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li><a href="<%=path %>/adm/home"><span class="am-icon-home"></span> 管理员后台</a></li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 平台管理模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                        <li><a href="<%=path %>/adm/updatepage" class="am-cf"><span class="am-icon-check"></span> 修改资料</a></li>
                        <li><a href="<%=path %>/adm/pwdpage"><span class="am-icon-puzzle-piece"></span> 修改密码</a></li>
                        <c:if test="${sessionScope.admin.role == 'super' }">
	                        <li><a href="<%=path %>/adm/regpage"><span class="am-icon-puzzle-piece"></span> 添加管理员</a></li>
                        	<li><a href="<%=path %>/admMan/admpager"><span class="am-icon-puzzle-piece"></span> 查看普通管理员</a></li>
                    	</c:if>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#col'}"><span class="am-icon-file"></span> 用户管理模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="col">
                        <li><a href="<%=path %>/adm/comp_check" class="am-cf"><span class="am-icon-check"></span> 装修公司审核 </a></li>
                        <li><a href="<%=path %>/adm/des_check"><span class="am-icon-calendar"></span> 设计师审核 </a></li>
                        <li><a href="<%=path %>/adm/sup_check"><span class="am-icon-th"></span> 建材商审核 </a></li>
                        <li><a href="<%=path %>/admCon/comCasepager"><span class="am-icon-puzzle-piece"></span> 装修公司案例 </a></li>
                        <li><a href="<%=path %>/admCon/comActpager"><span class="am-icon-puzzle-piece"></span> 装修公司活动 </a></li>
                        <li><a href="<%=path %>/admCon/propager"><span class="am-icon-th"></span> 建材商品 </a></li>
                        <li><a href="<%=path %>/admCon/supActpager"><span class="am-icon-th"></span> 建材商活动 </a></li>
                        <li><a href="<%=path %>/admCon/desCasepager"><span class="am-icon-calendar"></span> 设计师案例 </a></li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#look'}"><span class="am-icon-file"></span> 用户查看模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="look">
                        <li><a href="<%=path %>/admMan/compager" class="am-cf"><span class="am-icon-check"></span> 查看装修公司 </a></li>
                        <li><a href="<%=path %>/admMan/despager"><span class="am-icon-calendar"></span> 查看设计师 </a></li>
                        <li><a href="<%=path %>/admMan/suppager"><span class="am-icon-th"></span> 查看建材商 </a></li>
                        <li><a href="<%=path %>/admMan/userpager"><span class="am-icon-calendar"></span> 查看用户 </a></li>
                        <li><a href="<%=path %>/admMan/app"><span class="am-icon-calendar"></span> 查看预约 </a></li>
                    </ul>
                </li>
                <li><a href="<%=path %>/adm/exit"><span class="am-icon-sign-out"></span> 注销</a></li>
            </ul>
        </div>
    </div>