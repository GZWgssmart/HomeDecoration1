<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
	<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li><a href="<%=path %>/des/home"><span class="am-icon-home"></span> 设计师个人主页</a></li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span> 管理模块 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                        <li><a href="<%=path %>/des/updatepage" class="am-cf"><span class="am-icon-check"></span> 修改资料</a></li>
                        <li><a href="<%=path %>/des/pwdpage"><span class="am-icon-puzzle-piece"></span> 修改密码</a></li>
                        <li><a href="<%=path %>/des/des_casespage"><span class="am-icon-puzzle-piece"></span>案例列表</a></li>
                        <li><a href="<%=path %>/des/add_despage"><span class="am-icon-calendar"></span> 新建案例</a></li>
                    </ul>
                </li>
                <li><a href="<%=path %>/des/exit"><span class="am-icon-sign-out"></span> 注销</a></li>
            </ul>
        </div>
    </div>