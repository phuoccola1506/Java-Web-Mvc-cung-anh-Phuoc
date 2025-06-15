<%-- 
    Document   : header
    Created on : Jun 11, 2025, 12:04:08 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <li><a href="HomeServlet" class="active">Home</a></li>
                        <li><a href="ShopServlet">Our Shop</a></li>
                        <li><a href="Categories">Categories</a></li>
                        <li><a href="ContactServlet">Contact Us</a></li>
                        <li>
                            <c:if test="${sessionScope.user == null}">
                                <a href="LoginServlet">Sign In</a>
                            </c:if>
                                
                            <c:if test="${sessionScope.user != null}">
                                <a href="LogoutServlet">Sign Out</a>
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