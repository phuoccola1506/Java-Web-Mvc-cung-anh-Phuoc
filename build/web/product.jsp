<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <title>Lugx Gaming - Product Detail</title>

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
                        <h3>${product.name}</h3>
                        <span class="breadcrumb"><a href="#">Home</a>  >  <a href="#">Shop</a>  >  ${product.name}</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="single-product section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="left-image">
                            <img src="${product.thumbnail}" alt="">
                        </div>
                    </div>
                    <div class="col-lg-6 align-self-center">
                        <h4>${product.name}</h4>
                        <span class="price"><em>$${product.price + 20}</em> $${product.price}</span>
                        <p>${product.description}</p>
                        <form action="CartServlet" method="post">
                            <input type="hidden" name="action" value="create">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="price" value="${product.price}">
                            <input type="number" name="quantity" value="1" min="1">
                            <button type="submit">Add to Cart</button>
                        </form>
                        <ul>
                            <li><span>Game ID:</span> ${product.id}</li>
                            <li><span>Categories:</span> <a href="#">${category.name}</a></li>
                        </ul>
                    </div>
                    <div class="col-lg-12">
                        <div class="sep"></div>
                    </div>
                </div>
            </div>
        </div>

        <div class="more-info">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="tabs-content">
                            <div class="row">
                                <div class="nav-wrapper ">
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li class="nav-item" role="presentation">
                                            <button class="nav-link active" id="description-tab" data-bs-toggle="tab" data-bs-target="#description" type="button" role="tab" aria-controls="description" aria-selected="true">Description</button>
                                        </li>
                                        <li class="nav-item" role="presentation">
                                            <button class="nav-link" id="reviews-tab" data-bs-toggle="tab" data-bs-target="#reviews" type="button" role="tab" aria-controls="reviews" aria-selected="false">Reviews (3)</button>
                                        </li>
                                    </ul>
                                </div>              
                                <div class="tab-content" id="myTabContent">
                                    <div class="tab-pane fade show active" id="description" role="tabpanel" aria-labelledby="description-tab">
                                        <p>You can search for more templates on Google Search using keywords such as "templatemo digital marketing", "templatemo one-page", "templatemo gallery", etc. Please tell your friends about our website. If you need a variety of HTML templates, you may visit Tooplate and Too CSS websites.</p>
                                        <br>
                                        <p>Coloring book air plant shabby chic, crucifix normcore raclette cred swag artisan activated charcoal. PBR&B fanny pack pok pok gentrify truffaut kitsch helvetica jean shorts edison bulb poutine next level humblebrag la croix adaptogen. Hashtag poke literally locavore, beard marfa kogi bruh artisan succulents seitan tonx waistcoat chambray taxidermy. Same cred meggings 3 wolf moon lomo irony cray hell of bitters asymmetrical gluten-free art party raw denim chillwave tousled try-hard succulents street art.</p>
                                    </div>
                                    <div class="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
                                        <p>Coloring book air plant shabby chic, crucifix normcore raclette cred swag artisan activated charcoal. PBR&B fanny pack pok pok gentrify truffaut kitsch helvetica jean shorts edison bulb poutine next level humblebrag la croix adaptogen. <br><br>Hashtag poke literally locavore, beard marfa kogi bruh artisan succulents seitan tonx waistcoat chambray taxidermy. Same cred meggings 3 wolf moon lomo irony cray hell of bitters asymmetrical gluten-free art party raw denim chillwave tousled try-hard succulents street art.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="section categories related-games">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="section-heading">
                            <h6>Action</h6>
                            <h2>Related Games</h2>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="main-button">
                            <a href="shop.jsp">View All</a>
                        </div>
                    </div>
                    <c:forEach var="product" items="${NewProductList}">
                        <div class="col-lg col-sm-6 col-xs-12">
                            <div class="item">
                                <h4>${product.name}</h4>
                                <div class="thumb">
                                    <a href="ProductServlet?productId=${product.id}"><img src="${product.thumbnail}" alt="New Product Image" class="thumbnail-img"></a>
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