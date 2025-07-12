document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll('.qty-input').forEach(input => {
        input.addEventListener('change', function () {
            const productId = this.dataset.productId;
            const quantity = parseInt(this.value);

            if (quantity <= 0 || isNaN(quantity)) {
                alert("Số lượng không hợp lệ!");
                return;
            }

            fetch('CartServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: `action=update&productId=${productId}&quantity=${quantity}`
            })
                    .then(response => response.json())
                    .then(data => {
                        if (data.status === 'success') {
                            const formattedSubtotal = new Intl.NumberFormat('en-US', {
                                style: 'currency',
                                currency: 'USD'
                            }).format(data.subtotal);

                            const subtotalElement = document.getElementById('subtotal-' + data.productId);
                            if (subtotalElement) {
                                subtotalElement.innerText = formattedSubtotal;
                                subtotalElement.setAttribute("data-raw", data.subtotal);
                            }

                            updateTotalFromSubtotals();
                        } else {
                            alert("Cập nhật không thành công!");
                        }
                    })
                    .catch(error => {
                        console.error("Lỗi cập nhật:", error);
                        alert("Lỗi khi cập nhật sản phẩm!");
                    });
        });
    });

    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function () {
            const productId = this.dataset.productId;

            if (!confirm("Bạn có chắc chắn muốn xoá sản phẩm này?"))
                return;

            fetch('CartServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: `action=delete&productId=${productId}`
            })
                    .then(response => response.json())
                    .then(data => {
                        if (data.status === 'deleted') {
                            // Xoá phần tử container chứa toàn bộ thông tin sản phẩm
                            const productRow = document.querySelector(`.product-row[data-product-id="${productId}"]`);
                            if (productRow)
                                productRow.remove();

                            updateTotalFromSubtotals();
                        } else {
                            alert("Xoá không thành công!");
                        }
                    })
                    .catch(error => {
                        console.error("Lỗi xoá:", error);
                        alert("Lỗi khi xoá sản phẩm!");
                    });
        });
    });

    document.getElementById("delete-all-cart-btn")?.addEventListener("click", function () {
        if (!confirm("Bạn có chắc chắn muốn xóa toàn bộ giỏ hàng?"))
            return;

        fetch('CartServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-Requested-With': 'XMLHttpRequest'
            },
            body: 'action=clear'
        })
                .then(response => response.json())
                .then(data => {
                    if (data.status === 'cleared') {
                        // Xoá toàn bộ sản phẩm khỏi giao diện
                        document.querySelectorAll('.product-row').forEach(row => row.remove());

                        // Cập nhật lại tổng tiền
                        updateTotalFromSubtotals();
                    } else {
                        alert("Không thể xoá giỏ hàng!");
                    }
                })
                .catch(error => {
                    console.error("Lỗi khi xoá giỏ hàng:", error);
                    alert("Đã xảy ra lỗi khi xoá giỏ hàng!");
                });
    });


    function updateTotalFromSubtotals() {
        let total = 0;
        document.querySelectorAll('[id^="subtotal-"]').forEach(el => {
            const raw = parseFloat(el.dataset.raw || "0");
            total += raw;
        });

        const formattedTotal = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD'
        }).format(total);

        document.querySelectorAll('.total').forEach(el => {
            el.innerText = "Total: " + formattedTotal;
        });

        const cartSubtotal = document.querySelector('.cart-subtotal-text');
        if (cartSubtotal)
            cartSubtotal.innerText = formattedTotal;

        const cartTotal = document.querySelector('.cart-total-text');
        if (cartTotal)
            cartTotal.innerText = formattedTotal;
    }
});
