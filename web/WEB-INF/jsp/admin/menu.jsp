<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 14.02.2019
  Time: 01:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="page-sidebar" id="sidebar">
    <div id="sidebar-collapse">
        <div class="admin-block d-flex">
            <div>
                <img src="./assets/img/admin-avatar.png" width="45px"/>
            </div>
            <div class="admin-info">
                <div class="font-strong">${sessionScope.user.name} ${sessionScope.user.surname}</div>
                <small>${sessionScope.user.getRoleList().get(0).getName()}</small>
            </div>
        </div>
        <ul class="side-menu metismenu">
            <li>
                <a class="active" href="index.jsp"><i class="sidebar-item-icon fa fa-th-large"></i>
                    <span class="nav-label">İdarə paneli</span>
                </a>
            </li>
            <li>
                <a href="admin?action=showCustomerList"><i class="sidebar-item-icon fa fa-edit"></i>
                    <span class="nav-label">Müştərilər</span>
                </a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-table"></i>
                    <span class="nav-label">Sifarişlər</span>
                </a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-bar-chart"></i>
                    <span class="nav-label">Balans</span>
                </a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-map"></i>
                    <span class="nav-label">Mesajlar</span>
                </a>
            </li>
            <li>
                <a href="icons.html"><i class="sidebar-item-icon fa fa-smile-o"></i>
                    <span class="nav-label">Bildirişlər</span>
                </a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-envelope"></i>
                    <span class="nav-label">Ödənişlər</span>
                </a>
            </li>
            <li>
                <a href="calendar.html"><i class="sidebar-item-icon fa fa-calendar"></i>
                    <span class="nav-label">Bank hesabları</span>
                </a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-file-text"></i>
                    <span class="nav-label">Xərclər</span>
                </a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-file-text"></i>
                    <span class="nav-label">Hesabat</span>
                </a>
            </li>

            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-sitemap"></i>
                    <span class="nav-label">Çuvallar</span>
                </a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-bookmark"></i>
                    <span class="nav-label">Bağlamalar</span>
                </a>
            </li>
            <li>
                <a href="icons.html"><i class="sidebar-item-icon fa fa-smile-o"></i>
                    <span class="nav-label">Kuryer</span>
                </a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-envelope"></i>
                    <span class="nav-label">Gömrük</span>
                </a>
            </li>
            <li>
                <a href="calendar.html"><i class="sidebar-item-icon fa fa-calendar"></i>
                    <span class="nav-label">Anbar</span>
                </a>
            </li>

        </ul>
    </div>
</nav>
