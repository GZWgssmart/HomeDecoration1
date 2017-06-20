<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
	<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li><a href="<%=path %>/cus/home"><span class="am-icon-home"></span> 个人主页</a></li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 管理模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                        <li><a href="<%=path %>/cus/updatepage" class="am-cf"><span class="am-icon-check"></span> 修改资料</a></li>
                        <li><a href="<%=path %>/cus/pwdpage"><span class="am-icon-puzzle-piece"></span> 修改密码</a></li>
                        <li><a href="<%=path %>/cus/app"><span class="am-icon-th"></span> 预约记录</a></li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#save-nav'}"><span class="am-icon-file"></span> 收藏列表 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="save-nav">
                        <li><a href="<%=path %>/cusMan/desSave" class="am-cf"><span class="am-icon-check"></span> 设计师收藏</a></li>
                        <li><a href="<%=path %>/cusMan/desCaseSave" class="am-cf"><span class="am-icon-check"></span> 设计师案例收藏</a></li>
                        <li><a href="<%=path %>/cusMan/comSave"><span class="am-icon-th"></span> 装修公司收藏</a></li>
                        <li><a href="<%=path %>/cusMan/comCaseSave"><span class="am-icon-th"></span> 装修公司案例收藏</a></li>
                        <li><a href="<%=path %>/cusMan/supSave"><span class="am-icon-puzzle-piece"></span> 建材商收藏</a></li>
                        <li><a href="<%=path %>/cusMan/proSave"><span class="am-icon-calendar"></span> 建材商品收藏</a></li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#cart-nav'}"><span class="am-icon-file"></span> 购物车 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="cart-nav">
                        <li><a href="<%=path %>/cart/cart" class="am-cf"><span class="am-icon-check"></span> 购物车列表</a></li>
                        <li><a href="<%=path %>/cart/order" class="am-cf"><span class="am-icon-check"></span> 订单列表</a></li>
                        <li><a href="<%=path %>/cart/detail"><span class="am-icon-th"></span> 订单详情列表</a></li>
                    </ul>
                </li>
                <li><a href="<%=path %>/cus/exit"><span class="am-icon-sign-out"></span> 注销</a></li>
            </ul>
        </div>
    </div>