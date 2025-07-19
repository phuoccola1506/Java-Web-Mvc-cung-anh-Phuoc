<%-- 
    Document   : orderByMonth
    Created on : Jul 16, 2025, 9:08:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>DASHMIN - Bootstrap Admin Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/df02104330.js" crossorigin="anonymous"></script>

        <!-- Libraries Stylesheet -->
        <link href="./assets/admin/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="./assets/admin/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="./assets/admin/css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="./assets/admin/css/style.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-xxl position-relative bg-white d-flex p-0">

            <!-- Sidebar Start -->
            <%@include file="../inc/sidebar.jsp"%>
            <!-- Sidebar End -->


            <!-- Content Start -->
            <div class="content">
                <!-- Navbar Start -->
                <%@include file="../inc/navbar.jsp"%>
                <!-- Navbar End -->


                <!-- Table Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <div class="col-12">
                            <div class="bg-light rounded h-100 p-4">
                                <h5 class="mb-4">List order by month</h5>
                                <form method="get" action="ProductPiechartServlet" class="row g-3 align-items-end mb-4">
                                    <div class="col-auto">
                                        <label for="month" class="form-label">Month</label>
                                        <select name="month" id="month" class="form-select">
                                            <c:set var="monthNames" value="January,February,March,April,May,June,July,August,September,October,November,December" />
                                            <c:forEach var="m" begin="1" end="12" varStatus="loop">
                                                <c:set var="monthName" value="${fn:split(monthNames, ',')[loop.index - 1]}" />
                                                <option value="${m}" <c:if test="${m == selectedMonth}">selected</c:if>>
                                                    ${monthName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-auto">
                                        <label for="year" class="form-label">Year</label>
                                        <select name="year" id="year" class="form-select">
                                            <c:forEach var="y" begin="2020" end="2030">
                                                <option value="${y}" <c:if test="${y == selectedYear}">selected</c:if>>${y}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-auto">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </form>
                                <!--<canvas id="productPieChart" class="mb-4" width="200" height="200"></canvas>-->
                                <div class="row d-flex justify-content-center">
                                    <div class="col-6">
                                        <canvas id="productPieChart" class="mb-4" width="200" height="200"></canvas>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered">
                                        <thead class="table-light">
                                            <tr>
                                                <th>Tên sản phẩm</th>
                                                <th>Số lượng đã bán</th>
                                                <th>Tỷ lệ (%)</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="totalSold" value="0" />
                                            <!-- Tính tổng số lượng đã bán -->
                                            <c:forEach var="product" items="${productData}">
                                                <c:set var="totalSold" value="${totalSold + product.value}" />
                                            </c:forEach>

                                            <!-- Duyệt và hiển thị từng sản phẩm -->
                                            <c:forEach var="product" items="${productData}">
                                                <tr>
                                                    <td>${product.key}</td>
                                                    <td>${product.value}</td>
                                                    <td>
                                                        <fmt:formatNumber 
                                                            value="${(product.value * 100.0) / totalSold}" 
                                                            type="number" 
                                                            maxFractionDigits="2" 
                                                            />%
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr class="table-secondary fw-bold">
                                                <td>Tổng cộng</td>
                                                <td>${totalSold}</td>
                                                <td>100%</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Table End -->


                <!-- Footer Start -->
                <%@include file="../inc/footer.jsp"%>
                <!-- Footer End -->
            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="./assets/admin/lib/chart/chart.min.js"></script>
        <script src="./assets/admin/lib/easing/easing.min.js"></script>
        <script src="./assets/admin/lib/waypoints/waypoints.min.js"></script>
        <script src="./assets/admin/lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="./assets/admin/lib/tempusdominus/js/moment.min.js"></script>
        <script src="./assets/admin/lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="./assets/admin/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.2.0"></script>
        <script>
            const pieLabels = [<c:forEach var="entry" items="${productData}">"${entry.key}",</c:forEach>];
                    const pieData = [<c:forEach var="entry" items="${productData}">${entry.value},</c:forEach>];

            const ctxPie = document.getElementById('productPieChart').getContext('2d');
            const pieChart = new Chart(ctxPie, {
                type: 'pie',
                data: {
                    labels: pieLabels,
                    datasets: [{
                            label: 'Product Sales (%)',
                            data: pieData,
                            backgroundColor: [
                                '#FF6384', '#36A2EB', '#FFCE56',
                                '#4BC0C0', '#9966FF', '#FF9F40',
                                '#E7E9ED', '#8B0000', '#008000',
                                '#00008B', '#FF1493', '#00CED1'
                            ]
                        }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        title: {
                            display: true,
                            text: 'Sales Distribution by Product'
                        },
                        datalabels: {
                            formatter: (value, context) => {
                                const data = context.chart.data.datasets[0].data;
                                const total = data.reduce((sum, val) => sum + val, 0);
                                const percentage = ((value / total) * 100).toFixed(1);
                                return percentage + '%';
                            },
                            color: '#fff',
                            font: {
                                weight: 'bold',
                                size: 14
                            }
                        }
                    }
                },
                plugins: [ChartDataLabels]
            });
        </script>

    </body>

</html>