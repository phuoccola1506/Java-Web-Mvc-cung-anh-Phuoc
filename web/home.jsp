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

        <div class="main-banner">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 align-self-center">
                        <div class="caption header-text">
                            <h6>Welcome to lugx</h6>
                            <h2>BEST GAMING SITE EVER!</h2>
                            <p>LUGX Gaming is free Bootstrap 5 HTML CSS website template for your gaming websites. You can download and use this layout for commercial purposes. Please tell your friends about TemplateMo.</p>
                            <div class="search-input">
                                <form id="search" action="#">
                                    <input type="text" placeholder="Type Something" id='searchText' name="searchKeyword" onkeypress="handle" />
                                    <button role="button">Search Now</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 offset-lg-2">
                        <div class="right-image">
                            <img src="assets/images/banner-image.jpg" alt="">
                            <span class="price">$22</span>
                            <span class="offer">-40%</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="features">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <a href="#">
                            <div class="item">
                                <div class="image">
                                    <img src="assets/images/featured-01.png" alt="" style="max-width: 44px;">
                                </div>
                                <h4>Free Storage</h4>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <a href="#">
                            <div class="item">
                                <div class="image">
                                    <img src="assets/images/featured-02.png" alt="" style="max-width: 44px;">
                                </div>
                                <h4>User More</h4>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <a href="#">
                            <div class="item">
                                <div class="image">
                                    <img src="assets/images/featured-03.png" alt="" style="max-width: 44px;">
                                </div>
                                <h4>Reply Ready</h4>
                            </div>
                        </a>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <a href="#">
                            <div class="item">
                                <div class="image">
                                    <img src="assets/images/featured-04.png" alt="" style="max-width: 44px;">
                                </div>
                                <h4>Easy Layout</h4>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div class="section trending">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="section-heading">
                            <h6>Trending</h6>
                            <h2>Trending Games</h2>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="main-button">
                            <a href="ShopServlet">View All</a>
                        </div>
                    </div>
                    <c:forEach var="product" items="${HotProductList}">
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
                                    <form id="add-to-cart-${product.id}" action="CartServlet" method="post">
                                        <input type="hidden" name="action" value="create">
                                        <input type="hidden" name="productId" value="${product.id}">
                                        <input type="hidden" name="price" value="${product.price}">
                                        <input type="hidden" name="quantity" value="1">

                                        <a href="#" onclick="document.getElementById('add-to-cart-${product.id}').submit(); return false;">
                                            <i class="fa fa-shopping-bag"></i>
                                        </a>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="section most-played">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="section-heading">
                            <h6>TOP GAMES</h6>
                            <h2>Most Played</h2>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="main-button">
                            <a href="ShopServlet">View All</a>
                        </div>
                    </div>
                    <c:forEach var="product" items="${NewProductList}">
                        <div class="col-lg-2 col-md-6 col-sm-6">
                            <div class="item">
                                <div class="thumb">
                                    <a href="ProductServlet?productId=${product.id}">
                                        <img src="${product.thumbnail}" alt="Hot Product Image" class="thumbnail-img">
                                    </a>
                                </div>
                                <div class="down-content">
                                    <span class="category">${Categories[product.categoryId]}</span>
                                    <h4 class="truncate">${product.name}</h4>
                                    <a href="ProductServlet?productId=${product.id}">Explore</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="section categories">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="section-heading">
                            <h6>Categories</h6>
                            <h2>Top Categories</h2>
                        </div>
                    </div>
                    <c:forEach var="category" items="${TopCategories}">
                        <div class="col-lg col-sm-6 col-xs-12">
                            <div class="item">
                                <h4>${category.name}</h4>
                                <div class="thumb">
                                    <a href="CategoryServlet?categoryId=${category.id}"><img src="${category.thumbnail}" alt="" class="thumbnail-img"></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="section cta">
            <div class="container">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="shop">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="section-heading">
                                        <h6>Our Shop</h6>
                                        <h2>Go Pre-Order Buy & Get Best <em>Prices</em> For You!</h2>
                                    </div>
                                    <p>Lorem ipsum dolor consectetur adipiscing, sed do eiusmod tempor incididunt.</p>
                                    <div class="main-button">
                                        <a href="shop.html">Shop Now</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5 offset-lg-2 align-self-end">
                        <div class="subscribe">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="section-heading">
                                        <h6>NEWSLETTER</h6>
                                        <h2>Get Up To $100 Off Just Buy <em>Subscribe</em> Newsletter!</h2>
                                    </div>
                                    <div class="search-input">
                                        <form id="subscribe" action="#">
                                            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Your email...">
                                            <button type="submit">Subscribe Now</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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