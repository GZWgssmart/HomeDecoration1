<%@ page language="java" contentType="text/html; charset=utf-8}"
    pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
%>
	<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li><a href="<%=path %>/supply/home"><span class="am-icon-home"></span> 建材商主页</a></li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 管理模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                        <li><a href="<%=path %>/supply/updatepage" class="am-cf"><span class="am-icon-check"></span> 修改资料</a></li>
                        <li><a href="<%=path %>/supply/pwdpage"><span class="am-icon-puzzle-piece"></span> 修改密码</a></li>
                        <li><a href="<%=path %>/pro/pros"><span class="am-icon-th"></span> 商品列表</a></li>
                        <li><a href="<%=path %>/supAct/acts"><span class="am-icon-th"></span> 活动列表</a></li>
                        <li><a href="<%=path %>/pro/addpage"><span class="am-icon-calendar"></span> 添加商品</a></li>
                        <li><a href="<%=path %>/supAct/addpage"><span class="am-icon-calendar"></span> 添加活动</a></li>
                    </ul>
                </li>
                <li><a href="<%=path %>/supply/exit"><span class="am-icon-sign-out"></span> 注销</a></li>
            </ul>
        </div>
    </div>