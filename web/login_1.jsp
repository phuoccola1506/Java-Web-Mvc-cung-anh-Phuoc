<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>??ng nh?p</title>
        <!-- Nhúng Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        <!-- Nhúng Fontawesome -->
        <script src="https://kit.fontawesome.com/3341c17b89.js" crossorigin="anonymous"></script>
        <!-- Nhúng CSS -->
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <div id="login-n-register" class="d-flex justify-content-center align-items-center">
            <div class="wrapper alert">
                <form id="login-form" class="p-3 pt-1 text-light">
                    <h1 class="fs-2 text-center mt-1 mb-3">Login</h1>
                    <div class="input-box">
                        <input id="username" type="text" placeholder="Username">
                        <i class="fas fa-user"></i>
                    </div>
                    <div id="usernameError" class="text-danger d-none">Username không ???c ?? tr?ng</div>
                    <div class="input-box mb-2">
                        <input id="password" type="password" placeholder="Password">
                        <i class="fas fa-lock"></i>
                    </div>
                    <div id="passwordError" class="text-danger d-none">Password không ???c ?? tr?ng</div>
                    <div class="remember-n-forgot d-flex justify-content-between mb-2">
                        <label for="remember">
                            <input type="checkbox" id="remenber"> Remember me
                        </label>
                        <a href="#" class="text-light text-decoration-none">Forgot password?</a>
                    </div>
                    <div class="social-media-box row d-flex justify-content-between mb-3">
                        <div class="col-6">
                            <button id="btn-facebook" class="btn btn-primary w-100 border border-1 d-flex align-items-center justify-content-center">
                                <i class="fa-brands fa-facebook me-2"></i> Facebook
                            </button>
                        </div>
                        <div class="col-6">
                            <button id="btn-google" class="btn btn-danger w-100 border border-1 d-flex align-items-center justify-content-center">
                                <i class="fa-brands fa-google me-2"></i> Google
                            </button>
                        </div>
                        <!-- <div class="col-4">
                            <button id="btn-apple" class="btn btn-dark w-100 border border-1 d-flex align-items-center justify-content-center">
                                <i class="fa-brands fa-facebook me-2"></i> Tiktok
                            </button>
                        </div> -->
                    </div>
                    <button class="btn btn-dark w-100 bg-transparent border border-1 rounded-5 p-2" type="submit">Login</button>
                    <div class="register-link d-flex mt-2">
                        <p class="me-2">Don't have an account?</p>
                        <a href="register.html" class="text-light text-decoration-none">Register</a>
                    </div>
                </form>
            </div>
        </div>
        <!-- Nhúng JS -->
        <script src="../js/login.js"></script>
    </body>

</html>