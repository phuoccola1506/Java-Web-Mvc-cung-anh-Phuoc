<%-- 
    Document   : header
    Created on : Jun 11, 2025, 12:04:08 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Lấy servlet hiện tại -->
<c:set var="page" value="${pageContext.request.servletPath}" />

<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="HomeServlet" class="logo">
                        <img src="assets/images/logo.png" alt="" style="width: 158px;">
                    </a>
                    <!-- ***** Logo End ***** -->
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li><a href="HomeServlet" class="${page == '/HomeServlet' ? 'active' : ''}"><strong>Home</strong></a></li>
                        <li><a href="ShopServlet" class="${page == '/ShopServlet' ? 'active' : ''}"><strong>Our Shop</strong></a></li>
                        <li><a href="CategoriesServlet" class="${page == '/CategoriesServlet' ? 'active' : ''}"><strong>Categories</strong></a></li>
                        <li><a href="ContactServlet" class="${page == '/ContactServlet' ? 'active' : ''}"><strong>Contact Us</strong></a></li>
                    </ul>   
                    <ul class="nav">
                        <li><a href="CartServlet" class="${page == '/CartServlet' ? 'active' : ''}"><i class="fas fa-shopping-cart"></i> Cart</a></li>
                        <li class="login-logout">
                            <c:if test="${sessionScope.user == null}">
                                <a href="LoginServlet" class="${page == '/LoginServlet' ? 'active' : ''}"><i class="fas fa-user"></i> Sign In</a>
                            </c:if>
                                
                            <c:if test="${sessionScope.user != null}">
                                <a href="LogoutServlet"><i class="fas fa-user"></i> Sign Out</a>
                            </c:if>
                        </li>
                    </ul>
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ***** Header Area End ***** -->
