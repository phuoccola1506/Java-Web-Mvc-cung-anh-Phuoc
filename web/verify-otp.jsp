<%-- 
    Document   : verify-otp
    Created on : Jul 12, 2025, 5:40:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <%@include file="./inc/header.jsp" %>

        <div class="page-heading header-text">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h3>Login</h3>
                        <span class="breadcrumb"><a href="#">Home</a> > Login</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="otp-container">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10">
                        <div class="card otp-card">

                            <!-- Header -->
                            <div class="card-header text-center">
                                <i class="fas fa-shield-alt fa-3x mb-3"></i>
                                <h2 class="fw-bold mb-2">Two-Factor Authentication</h2>
                                <p class="mb-0 opacity-90">Secure your account with OTP verification</p>
                            </div>

                            <!-- Body -->
                            <div class="card-body p-4">

                                <!-- Step 1 -->
                                <div class="text-center mb-4">
                                    <div class="step-indicator">1</div>
                                    <h5 class="fw-bold text-primary">Scan QR Code or enter Secret Key</h5>
                                    <p class="text-muted">Use Google Authenticator or Authy app</p>
                                </div>

                                <div class="qr-section text-center">
                                    <div class="qr-code mb-4">
                                        <img src="https://api.qrserver.com/v1/create-qr-code/?size=220x220&data=${otpAuthURL}" 
                                             alt="QR Code for 2FA" 
                                             class="img-fluid" />
                                    </div>

                                    <div class="secret-key mb-3">
                                        <small class="text-muted d-block mb-2">
                                            <i class="fas fa-key"></i> Secret Key:
                                        </small>
                                        <strong class="text-primary">${secretKey}</strong>
                                    </div>

                                    <small class="text-muted">
                                        <i class="fas fa-info-circle"></i> 
                                        Save the secret key in a safe place for recovery when needed
                                    </small>
                                </div>

                                <!-- Step 2 -->
                                <div class="text-center mb-4">
                                    <div class="step-indicator">2</div>
                                    <h5 class="fw-bold text-primary">Enter OTP Code</h5>
                                    <p class="text-muted">Enter the 6-digit code from authenticator app</p>
                                </div>

                                <form action="VerifyOTPServlet" method="post" class="needs-validation" novalidate>
                                    <div class="row justify-content-center">
                                        <div class="col-md-6">
                                            <div class="mb-4">
                                                <div class="input-group">
                                                    <span class="input-group-text bg-light border-end-0">
                                                        <i class="fas fa-mobile-alt text-primary"></i>
                                                    </span>
                                                    <input type="text" 
                                                           name="otp" 
                                                           class="form-control border-start-0" 
                                                           placeholder="000000" 
                                                           maxlength="6"
                                                           pattern="[0-9]{6}"
                                                           required 
                                                           autocomplete="off" />
                                                    <div class="invalid-feedback">
                                                        Please enter a 6-digit OTP code
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="d-grid">
                                                <button type="submit" class="btn btn-verify btn-lg">
                                                    <i class="fas fa-check-circle me-2"></i>
                                                    Verify OTP
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <!-- Features -->
                                <div class="row mt-5">
                                    <div class="col-md-4 text-center mb-3">
                                        <i class="fas fa-lock feature-icon"></i>
                                        <h6 class="fw-bold">High Security</h6>
                                        <small class="text-muted">End-to-end encryption</small>
                                    </div>
                                    <div class="col-md-4 text-center mb-3">
                                        <i class="fas fa-clock feature-icon"></i>
                                        <h6 class="fw-bold">Real-time</h6>
                                        <small class="text-muted">OTP changes every 30s</small>
                                    </div>
                                    <div class="col-md-4 text-center mb-3">
                                        <i class="fas fa-mobile-alt feature-icon"></i>
                                        <h6 class="fw-bold">Multi-platform</h6>
                                        <small class="text-muted">iOS, Android, Desktop</small>
                                    </div>
                                </div>
                            </div>

                            <!-- Footer -->
                            <div class="card-footer bg-light text-center">
                                <small class="text-muted">
                                    <i class="fas fa-question-circle"></i>
                                    Need help? 
                                    <a href="#" class="text-decoration-none">Contact support</a>
                                </small>
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
        <script src="assets/js/verify-otp.js"></script>

    </body>
</html>

