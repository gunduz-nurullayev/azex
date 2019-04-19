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
                <a class="adresses" href="customer?action=address"><i class="sidebar-item-icon fa fa-th-large"></i>
                    <span class="nav-label">Xaricdəki Ünvanlarım</span>
                </a>
            </li>
            <li>
                <a href="customer?action=orders"><i class="sidebar-item-icon fa fa-calendar"></i>
                    <span class="nav-label">Sifarişlərim</span>
                </a>
            </li>
            <li>
                <a href="customer?action=balance"><i class="sidebar-item-icon fa fa-file-text"></i>
                    <span class="nav-label">Balans</span></a>
            </li>
            <li>
                <a href="customer?action=messages"><i class="sidebar-item-icon fa fa-sitemap"></i>
                    <span class="nav-label">Mesajlarım</span></a>
            </li>
            <li>
                <a href="customer?action=declarations"><i class="sidebar-item-icon fa fa-sitemap"></i>
                    <span class="nav-label">Bəyannamələrim</span></a>
            </li>
            <li>
                <a href="javascript:;"><i class="sidebar-item-icon fa fa-bookmark"></i>
                    <span class="nav-label">Tənzimləmələr</span><i class="fa fa-angle-left arrow"></i></a>
                <ul class="nav-2-level collapse">
                    <li>
                        <a href="customer?action=profile">Profil</a>
                    </li>
                    <li>
                        <a href="customer?action=change-password">Şifrəni dəyiş</a>
                    </li>
                </ul>
            </li>
        </ul>
            <div class="son30gun" style="background-color:#fff;border-radius: 10px; box-shadow: 0 6px 12px rgba(0, 0, 0, .175); margin-top: 20px;">
                <div class="title" style="border-radius: 10px 10px 0px 0px; background-color: #090d2a; color:  #fff; text-align:  center; font-weight:  bold; font-size:  19px; height: 35px; line-height: 35px;">Balansım</div>
                <div class="body" style="height:  150px;font-weight:bold;text-align:center;line-height:150px;font-size: 45px;text-shadow:  2px 2px #000;color: #090d2a;"><a href="http://localhost:8080/azex/balance">${sessionScope.balance} AZN</a></div>
                <div class="nedir" style="text-align:  center;">
                </div>
            </div>
    </div>
</nav>
