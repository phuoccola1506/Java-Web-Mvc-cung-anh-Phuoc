<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <title>Lugx Gaming Shop HTML5 Template</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-lugx-gaming.css?v=2">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/style.css?v=3">
        <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
        <!--
        
        TemplateMo 589 lugx gaming
        
        https://templatemo.com/tm-589-lugx-gaming
        
        -->
    </head>

    <body>

        <!-- ***** Preloader Start ***** -->
        <div id="js-preloader" class="js-preloader">
            <div class="preloader-inner">
                <span class="dot"></span>
                <div class="dots">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
        </div>
        <!-- ***** Preloader End ***** -->

        <%@include file="./inc/header.jsp" %>

        <div class="page-heading header-text">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h3>${category.name}</h3>
                        <span class="breadcrumb"><a href="#">Home</a> > <a href="#">Categories</a> > ${category.name}</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="section trending">
            <div class="container">
                <div class="row mb-4">
                    <div class="col-lg-6">
                        <form action="CategoryServlet" class="filter d-flex align-items-center" method="get">
                            <input type="hidden" name="categoryId" value="${category.id}">
                            
                            <strong class="me-2">Sort by:</strong>
                            <select name="property" id="property" class="form-control me-3">
                                <option value="name">Name</option>
                                <option value="price">Price</option>
                                <option value="createdAt">Time</option>
                            </select>

                            <strong class="me-2">Order by:</strong>
                            <select name="order" id="order" class="form-control me-3">
                                <option value="asc">A-Z</option>
                                <option value="desc">Z-A</option>
                            </select>

                            <button type="submit" class="btn btn-primary">Filter</button>
                        </form>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-heading">
                            <h6>${category.name}</h6>
                            <h2>${category.name} of Games</h2>
                        </div>
                    </div>
                    <c:forEach var="product" items="${ProductList}">
                        <div class="col-lg-3 col-md-6">
                            <div class="item">
                                <div class="thumb">
                                    <a href="ProductServlet?productId=${product.id}">
                                        <img src="${product.thumbnail}" alt="Hot Product Image" class="thumbnail-img">
                                    </a>
                                    <span class="price">$${product.price}</span>
                                </div>
                                <div class="down-content">
                                    <span class="category">${category.name}</span>
                                    <h4 class="truncate" title="${product.name}">${product.name}</h4>
                                    <a href="ProductServlet?productId=${product.id}"><i class="fa fa-shopping-bag"></i></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <%@include file="./inc/footer.jsp" %>

        <!-- Scripts -->
        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/isotope.min.js"></script>
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/counter.js"></script>
        <script src="assets/js/custom.js"></script>

    </body>
</html>