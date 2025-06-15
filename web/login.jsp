<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <title>Lugx Gaming - Login</title>

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
        <div class="d-flex justify-content-center align-items-center min-vh-100 bg-primary">
            <div class="wrapper alert alert-dark">
                <form action="LoginServlet" method="post" class="p-3 pt-1 text-light">
                    <h1 class="fs-2 text-center mb-4">Login</h1>
                    <span class="error">${error}</span>
                    <div class="mb-3 position-relative">
                        <input name="email" type="email" class="form-control" placeholder="Email" required>
                        <i class="fa-solid fa-envelope position-absolute top-50 end-0 me-3 translate-middle-y"></i>
                    </div>
                    <div class="mb-3 position-relative">
                        <input name="password" type="password" class="form-control" placeholder="Password" required>
                        <i class="fas fa-lock position-absolute top-50 end-0 me-3 translate-middle-y"></i>
                    </div>
                    <div class="d-flex justify-content-between mb-3">
                        <label><input type="checkbox" name="remember"> Remember me</label>
                        <a href="#" class="text-light">Forgot password?</a>
                    </div>
                    <button class="btn btn-primary w-100 mb-3" type="submit">Login</button>
                    <div class="text-center">
                        <span>Don't have an account?</span>
                        <a href="register.jsp" class="text-light">Register</a>
                    </div>
                </form>
            </div>
        </div>
        <%@include file="./inc/footer.jsp" %>
    </body>
</html>
