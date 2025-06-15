<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <title>Lugx Gaming - Cart</title>

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

        <%@include file="./inc/header.jsp" %>
        
        <div class="main-banner">
        </div>
        
        <div class="container py-5">
            <h2 class="mb-4">Your Shopping Cart</h2>

            <c:if test="${empty cartItems}">
                <div class="alert alert-info">Your cart is empty.</div>
            </c:if>

            <c:if test="${not empty cartItems}">
                <form action="UpdateCartServlet" method="post">
                    <table class="table table-bordered align-middle text-center">
                        <thead class="table-light">
                            <tr>
                                <th>Product</th>
                                <th>Thumbnail</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Subtotal</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="total" value="0" />
                            <c:forEach var="item" items="${cartItems}">
                                <tr>
                                    <td>${item.product.name}</td>
                                    <td><img src="${item.product.thumbnail}" alt="" width="60"></td>
                                    <td><fmt:formatNumber value="${item.product.price}" type="currency"/></td>
                                    <td>
                                        <input type="number" name="quantities[${item.product.id}]" value="${item.quantity}" min="1" class="form-control w-50 mx-auto"/>
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${item.product.price * item.quantity}" type="currency"/>
                                    </td>
                                    <td>
                                        <a href="RemoveFromCartServlet?productId=${item.product.id}" class="btn btn-danger btn-sm">Remove</a>
                                    </td>
                                </tr>
                                <c:set var="total" value="${total + (item.product.price * item.quantity)}" />
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <a href="ShopServlet" class="btn btn-secondary">Continue Shopping</a>
                        </div>
                        <div>
                            <strong>Total:</strong> <fmt:formatNumber value="${total}" type="currency"/>
                            <button type="submit" class="btn btn-primary ms-3">Update Cart</button>
                            <a href="CheckoutServlet" class="btn btn-success ms-2">Checkout</a>
                        </div>
                    </div>
                </form>
            </c:if>
        </div>
        
        <%@include file="./inc/footer.jsp" %>

    </body>
</html>
