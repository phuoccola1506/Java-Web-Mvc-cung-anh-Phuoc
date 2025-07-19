/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// OTP Input handling
const otpInputs = document.querySelectorAll('.otp-input');
const verifyBtn = document.getElementById('verify-btn');
const resendBtn = document.getElementById('resend-btn');
const resultMessage = document.getElementById('result-message');
let timerInterval;

// Auto-focus and navigation
otpInputs.forEach((input, index) => {
    input.addEventListener('input', function (e) {
        // Only allow numbers
        this.value = this.value.replace(/[^0-9]/g, '');

        if (this.value.length === 1) {
            this.classList.add('filled');
            // Move to next input
            if (index < otpInputs.length - 1) {
                otpInputs[index + 1].focus();
            }
        } else {
            this.classList.remove('filled');
        }

        checkOTPComplete();
    });

    input.addEventListener('keydown', function (e) {
        // Backspace handling
        if (e.key === 'Backspace' && this.value === '' && index > 0) {
            otpInputs[index - 1].focus();
        }
    });

    input.addEventListener('paste', function (e) {
        e.preventDefault();
        const pastedData = e.clipboardData.getData('text').replace(/[^0-9]/g, '');

        if (pastedData.length === 6) {
            otpInputs.forEach((inp, idx) => {
                if (idx < pastedData.length) {
                    inp.value = pastedData[idx];
                    inp.classList.add('filled');
                }
            });
            checkOTPComplete();
        }
    });
});

function checkOTPComplete() {
    const allFilled = Array.from(otpInputs).every(input => input.value.length === 1);
    verifyBtn.disabled = !allFilled;
}

// Timer functionality
function startTimer(duration) {
    let timer = duration;
    const timerElement = document.getElementById('timer');

    timerInterval = setInterval(function () {
        const minutes = Math.floor(timer / 60);
        const seconds = timer % 60;

        timerElement.textContent =
                (minutes < 10 ? '0' + minutes : minutes) + ':' +
                (seconds < 10 ? '0' + seconds : seconds);

        if (--timer < 0) {
            clearInterval(timerInterval);
            timerElement.textContent = '00:00';
            showResult('Mã OTP đã hết hạn. Vui lòng yêu cầu mã mới.', 'error');
            otpInputs.forEach(input => input.disabled = true);
            verifyBtn.disabled = true;
        }
    }, 1000);
}

// Verify OTP
verifyBtn.addEventListener('click', function () {
    const otpValue = Array.from(otpInputs).map(input => input.value).join('');

    // Simulate OTP verification (replace with actual verification logic)
    if (otpValue === '123456') {
        showResult('✅ Xác thực OTP thành công!', 'success');
        clearInterval(timerInterval);
        verifyBtn.textContent = '✓ Đã xác thực';
        verifyBtn.disabled = true;
    } else {
        showResult('❌ Mã OTP không chính xác. Vui lòng thử lại.', 'error');
        clearOTP();
    }
});

// Resend OTP
resendBtn.addEventListener('click', function () {
    clearOTP();
    clearInterval(timerInterval);
    startTimer(300); // 5 minutes
    showResult('📱 Mã OTP mới đã được gửi!', 'success');
    setTimeout(() => {
        resultMessage.style.display = 'none';
    }, 3000);
});

function clearOTP() {
    otpInputs.forEach(input => {
        input.value = '';
        input.classList.remove('filled');
        input.disabled = false;
    });
    otpInputs[0].focus();
    verifyBtn.disabled = true;
    verifyBtn.innerHTML = '<i class="fas fa-check-circle me-2"></i>Xác thực OTP';
}

function showResult(message, type) {
    resultMessage.innerHTML = `<div class="result-message ${type}">${message}</div>`;
    resultMessage.style.display = 'block';
}

// Initialize
document.addEventListener('DOMContentLoaded', function () {
    startTimer(300); // 5 minutes
    otpInputs[0].focus();
});