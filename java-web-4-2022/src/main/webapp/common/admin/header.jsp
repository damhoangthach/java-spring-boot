<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="com.laptrinhjavaweb.security.utils.SecurityUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="navbar" class="navbar navbar-default          ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Trang quản trị
                </small>
            </a>
            <nav role="navigation" class="navbar-menu pull-left collapse navbar-collapse">
                <ul class="nav navbar-nav">


                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Quản lý Toà Nhà
                                &nbsp;
                                <i class="ace-icon fa fa-angle-down bigger-110"></i>
                            </a>

                            <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                <li>
                                    <a href='<c:url value='/admin/building-list'/>'>
                                        <i class="ace-icon fa fa-eye bigger-110 blue"></i>
                                        Danh sách Toà Nhà
                                    </a>
                                </li>

                                <li>
                                    <a href='<c:url value='/admin/building-edit'/>'>
                                        <i class="ace-icon fa fa-user bigger-110 blue"></i>
                                        Tạo Toà Nhà
                                    </a>
                                </li>

                            </ul>
                        </li>



                            <li>
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    Quản lý Khách Hàng
                                    &nbsp;
                                    <i class="ace-icon fa fa-angle-down bigger-110"></i>
                                </a>

                                <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                    <li>
                                        <a href='<c:url value='/admin/customer-list'/>'>
                                            <i class="ace-icon fa fa-eye bigger-110 blue"></i>
                                          Danh sách Khách hàng
                                        </a>
                                    </li>


                                </ul>
                            </li>

                            <li>
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    Quản lý Tài khoản
                                    &nbsp;
                                    <i class="ace-icon fa fa-angle-down bigger-110"></i>
                                </a>

                                <ul class="dropdown-menu dropdown-light-blue dropdown-caret">
                                    <li>
                                        <a href='<c:url value='/admin/user-list'/>'>
                                            <i class="ace-icon fa fa-eye bigger-110 blue"></i>
                                            Danh sách tài khoản
                                        </a>
                                    </li>

                                </ul>
                            </li>










                    <li>
                        <a href="#">
                            <i class="ace-icon fa fa-envelope"></i>
                            Thông Báo
                            <span class="badge badge-warning">5</span>
                        </a>
                    </li>
                </ul>

            </nav>
        </div>





        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        Xin chào, <%=SecurityUtils.getPrincipal().getFullName()%>
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="/admin/profile-<%=SecurityUtils.getPrincipal().getUsername()%>">
                                <i class="ace-icon fa fa-user"></i>
                                <%--<spring:message code="label.account.information"/>--%>
                                Thông tin tài khoản
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value="/admin/profile-password"/>">
                                <i class="ace-icon fa fa-key"></i>
                                <%--<spring:message code="label.password.change"/>--%>
                                Đổi mật khẩu
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value='/logout'/>">
                                <i class="ace-icon fa fa-power-off"></i>
                                Thoát
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>