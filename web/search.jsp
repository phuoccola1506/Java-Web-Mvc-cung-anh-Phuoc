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
        <link rel="stylesheet" href="assets/css/templatemo-lugx-gaming.css">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/style.css">
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
                        <h3>#${keyword}</h3>
                        <span class="breadcrumb"><a href="#">Home</a> > <a href="#">Shop</a> > #${keyword}</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="section search-bar bg-light">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <form action="SearchServlet" method="get" class="d-flex shadow rounded overflow-hidden bg-white">
                            <input type="text" name="keyword" class="form-control border-0 px-4 py-3" placeholder="Search for games..." required>
                            <button type="submit" class="btn btn-primary px-4">Search</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="section trending">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-heading">
                            <h6>#${keyword}</h6>
                            <h2>Search of #${keyword}</h2>
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
                                    <span class="category">${Categories[product.categoryId]}</span>
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