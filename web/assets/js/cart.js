/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener("DOMContentLoaded", function () {
    rebindEventListeners(); // Lần đầu khi trang vừa load

    const deleteAllBtn = document.getElementById("delete-all-cart-btn");
    if (deleteAllBtn) {
        deleteAllBtn.addEventListener("click", function () {
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
                            reloadCartContent();
                        } else {
                            alert("Không thể xoá giỏ hàng!");
                        }
                    })
                    .catch(error => {
                        console.error("Lỗi khi xoá giỏ hàng:", error);
                        alert("Đã xảy ra lỗi khi xoá giỏ hàng!");
                    });
        });
    }
});

function rebindEventListeners() {
    // Gắn lại sự kiện cập nhật số lượng
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
                            reloadCartContent();
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

    // Gắn lại sự kiện nút xoá
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
                            reloadCartContent();
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
}

function reloadCartContent() {
    fetch('CartServlet')
            .then(response => response.text())
            .then(html => {
                const parser = new DOMParser();
                const doc = parser.parseFromString(html, 'text/html');
                const newCart = doc.querySelector('#cart-container');
                const currentCart = document.querySelector('#cart-container');

                if (newCart && currentCart) {
                    currentCart.innerHTML = newCart.innerHTML;
                    rebindEventListeners(); // Gắn lại các sự kiện sau khi DOM thay đổi
                }
            })
            .catch(error => {
                console.error("Lỗi khi tải lại giỏ hàng:", error);
            });
}
