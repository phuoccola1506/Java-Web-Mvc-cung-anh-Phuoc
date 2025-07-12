<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <title>Lugx Gaming - Cart</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-lugx-gaming.css?v=2">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/style.css?v=3">
        <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>


        <!--TemplateMo 589 lugx gaming-->

        <!--https://templatemo.com/tm-589-lugx-gaming-->


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
        <!--***** Preloader End *****--> 

        <%@include file="./inc/header.jsp"%>

        <div class="page-heading header-text">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h3>Cart</h3>
                        <span class="breadcrumb"><a href="#">Home</a> > Cart</span>
                    </div>
                </div>
            </div>
        </div>

        <div id="cart-container">
            <!-- Cart Content -->
            <div class="container my-5">
                <!-- Page Title -->
                <div class="row">
                    <div class="col-12">
                        <h5 class="display-6 text-start mb-4">
                            <i class="fas fa-shopping-cart"></i>
                            Your Shopping Cart
                        </h5>
                        <hr class="mb-4">
                    </div>
                </div>

                <!-- Cart Items -->
                <div class="row">
                    <div class="col-lg-8">
                        <div class="card shadow-sm">
                            <div class="card-header bg-primary">
                                <h5 class="mb-0 text-light">
                                    <i class="fas fa-list"></i> Products List
                                </h5>
                            </div>
                            <div class="card-body">
                                <!-- Cart Item -->
                                <c:forEach items="${cart}" var="orderItem">
                                    <div class="row align-items-center border-bottom py-3 product-row" data-product-id="${orderItem.productId}">
                                        <div class="col-md-2">
                                            <img src="${orderItem.product.thumbnail}" alt="MG Barbatos" class="img-fluid rounded">
                                        </div>
                                        <div class="col-md-4">
                                            <h6 class="fw-bold">${orderItem.product.name}</h6>
                                        </div>
                                        <div class="col-md-1">
                                            <h6 class="fw-bold">${orderItem.product.price}</h6>
                                        </div>
                                        <div class="col-md-2">
                                            <input 
                                                type="number" 
                                                min="1" 
                                                value="${orderItem.quantity}" 
                                                class="qty-input form-control" 
                                                data-product-id="${orderItem.productId}" />
                                        </div>
                                        <div class="col-md-2 subtotal" id="subtotal-${orderItem.productId}" data-raw="${orderItem.price * orderItem.quantity}">
                                            $<fmt:formatNumber value="${orderItem.price * orderItem.quantity}" type="number" minFractionDigits="2" />
                                        </div>
                                        <div class="col-md-1">
                                            <button type="button" 
                                                    class="btn btn-outline-danger btn-sm delete-btn" 
                                                    data-product-id="${orderItem.productId}">
                                                <i class="fas fa-trash"></i>
                                            </button>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="card-footer">
                                <div class="d-flex justify-content-between">
                                    <a href="ShopServlet" class="btn btn-outline-primary">
                                        <i class="fas fa-arrow-left"></i> Continue Shopping
                                    </a>
                                    <button class="btn btn-outline-danger" id="delete-all-cart-btn">
                                        <i class="fas fa-trash"></i> Delete All Cart
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Order Summary -->
                    <div class="col-lg-4">
                        <div class="card shadow-sm">
                            <div class="card-header bg-danger">
                                <h5 class="mb-0 text-light">
                                    <i class="fas fa-calculator"></i> Order Summary
                                </h5>
                            </div>
                            <div class="card-body">
                                <div class="d-flex justify-content-between mb-2">
                                    <span>Cart Subtotal:</span>
                                    <span class="cart-subtotal-text">$${total}</span>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <span>Transportation Fee:</span>
                                    <span>50,000₫</span>
                                </div>
                                <div class="d-flex justify-content-between mb-2">
                                    <span>Discount:</span>
                                    <span class="text-success">-100,000₫</span>
                                </div>
                                <hr>
                                <div class="d-flex justify-content-between mb-3">
                                    <strong>Cart Total:</strong>
                                    <strong class="text-primary fs-5 cart-total-text">$${total}</strong>
                                </div>

                                <!-- Discount Code -->
                                <div class="mb-3">
                                    <label for="discountCode" class="form-label">Discount code:</label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="discountCode" placeholder="">
                                        <button class="btn btn-outline-secondary" type="button">Apply</button>
                                    </div>
                                </div>

                                <!-- Checkout Button -->
                                <div class="d-grid">
                                    <a href="CheckoutServlet" class="btn btn-primary btn-lg">
                                        <i class="fas fa-credit-card"></i> Process to Checkout
                                    </a>
                                </div>
                            </div>
                        </div>

                        <!-- Payment Methods -->
                        <div class="card shadow-sm mt-3">
                            <div class="card-header">
                                <h6 class="mb-0">
                                    <i class="fas fa-credit-card"></i> Payment method
                                </h6>
                            </div>
                            <div class="card-body">
                                <div class="d-flex justify-content-around">
                                    <i class="fab fa-cc-visa fa-2x text-primary"></i>
                                    <i class="fab fa-cc-mastercard fa-2x text-warning"></i>
                                    <i class="fab fa-cc-paypal fa-2x text-info"></i>
                                    <i class="fas fa-mobile-alt fa-2x text-success"></i>
                                </div>
                                <small class="text-muted d-block text-center mt-2">
                                    100% secure payment
                                </small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="./inc/footer.jsp"%>

        <c:if test="${not empty sessionScope.message}">
            <script>alert("${sessionScope.message}");</script>
            <c:remove var="message" scope="session" />
        </c:if>


        <!-- Scripts -->
        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/isotope.min.js"></script>
        <script src="assets/js/owl-carousel.js"></script>
        <script src="assets/js/counter.js"></script>
        <script src="assets/js/custom.js"></script>
        <script src="assets/js/updateproduct.js"></script>
        <script src="assets/js/cart.js"></script>

    </body>

</html>
