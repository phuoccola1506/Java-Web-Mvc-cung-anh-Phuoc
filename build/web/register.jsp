<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <title>Lugx Gaming - Register</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Additional CSS Files -->
        <link rel="stylesheet" href="assets/css/fontawesome.css">
        <link rel="stylesheet" href="assets/css/templatemo-lugx-gaming.css?v=3">
        <link rel="stylesheet" href="assets/css/owl.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/style.css?v=3">
        <link rel="stylesheet" href="assets/css/login-logout.css?v=3">
        <link rel="stylesheet"href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
        <!--
        
        TemplateMo 589 lugx gaming
        
        https://templatemo.com/tm-589-lugx-gaming
        
        -->
    </head>
    <body>
        <%--<%@include file="./inc/header.jsp" %>--%>

        <!--        <div class="page-heading header-text rounded-0">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <h3>Login</h3>
                                <span class="breadcrumb"><a href="#">Home</a> > Register</span>
                            </div>
                        </div>
                    </div>
                </div>-->

        <div id="login-n-register" class="d-flex justify-content-center align-items-center min-vh-100">
            <div class="wrapper alert">
                <div class="d-flex justify-content-center mb-2">
                    <a href="HomeServlet" class="logo">
                        <img src="assets/images/logo.png" alt="" style="width: 158px;">
                    </a>
                </div>
                <form action="RegisterServlet" method="post" id="register-form" class="p-3 pt-1 text-light">
                    <h1 class="fs-2 text-center text-light mt-1 mb-3">Registration</h1>

                    <span class="error text-danger">${error}</span>

                    <!-- Username -->
                    <div class="input-box">
                        <input id="username" name="username" type="text" placeholder="Username" required>
                        <i class="fas fa-user"></i>
                    </div>
                    <div id="usernameError" class="text-danger d-none">Username is required!</div>

                    <!-- Email -->
                    <div class="input-box">
                        <input id="email" name="email" type="email" placeholder="Email" required>
                        <i class="fa-solid fa-envelope"></i>
                    </div>
                    <div id="emailError" class="text-danger d-none">Email is required!</div>

                    <!-- Password -->
                    <div class="input-box mb-2">
                        <input id="password" name="password" type="password" placeholder="Password" required>
                        <i class="fas fa-lock"></i>
                    </div>
                    <div id="passwordError" class="text-danger d-none">Password is required!</div>

                    <!-- Confirm Password -->
                    <div class="input-box mb-2">
                        <input id="confirm-password" name="confirmPassword" type="password" placeholder="Confirm Password" required>
                        <i class="fas fa-lock"></i>
                    </div>
                    <div id="confirmPasswordError" class="text-danger d-none">Confirm password is required!</div>

                    <!-- Submit Button -->
                    <button class="btn btn-dark w-100 bg-transparent border border-1 rounded-5 p-2 mt-3" type="submit">
                        Register
                    </button>

                    <!-- Login Link -->
                    <div class="register-link d-flex justify-content-center mt-2">
                        <p class="text-light me-2">Already have an account?</p>
                        <a href="LoginServlet" class="text-light text-decoration-none">Login</a>
                    </div>
                </form>
            </div>
        </div>

        <%--<%@include file="./inc/footer.jsp" %>--%>

    </body>
</html>
