document.addEventListener('DOMContentLoaded', function() {
    const calculatorForm = document.getElementById('calculatorForm');
    const calculatorResult = document.getElementById('calculatorResult');
    const tuitionFeeInput = document.getElementById('tuitionFee');
    const booksCostInput = document.getElementById('booksCost');
    const currentLanguage = 'en'; // Assume 'en' as default, you can change based on user preference

    function validateInput(input) {
        const value = parseFloat(input.value);
        if (isNaN(value) || value < 0) {
            input.classList.add('is-invalid');
            return false;
        }
        input.classList.remove('is-invalid');
        return true;
    }

    tuitionFeeInput.addEventListener('input', () => validateInput(tuitionFeeInput));
    booksCostInput.addEventListener('input', () => validateInput(booksCostInput));

    calculatorForm.addEventListener('submit', function(e) {
        e.preventDefault();

        if (!validateInput(tuitionFeeInput) || !validateInput(booksCostInput)) {
            calculatorResult.innerHTML = `<p class="text-danger">${translations[currentLanguage].enterValidNumbers}</p>`;
            return;
        }

        const tuitionFee = parseFloat(tuitionFeeInput.value);
        const booksCost = parseFloat(booksCostInput.value);
        const totalCost = tuitionFee + booksCost;
        let scholarshipAmount = 0;

        if (totalCost <= 50000) {
            scholarshipAmount = totalCost * 0.5;
        } else if (totalCost <= 100000) {
            scholarshipAmount = 25000 + (totalCost - 50000) * 0.4;
        } else {
            scholarshipAmount = 45000 + (totalCost - 100000) * 0.3;
        }

        const result = `
            <h4>${translations[currentLanguage].scholarshipCalculationResult}</h4>
            <p>${translations[currentLanguage].totalCost} ₹${totalCost.toFixed(2)}</p>
            <p>${translations[currentLanguage].scholarshipAmount} ₹${scholarshipAmount.toFixed(2)}</p>
            <p>${translations[currentLanguage].amountToPay} ₹${(totalCost - scholarshipAmount).toFixed(2)}</p>
        `;

        calculatorResult.innerHTML = result;
        calculatorResult.classList.add('animate__animated', 'animate__fadeIn');

        // Remove animation classes after animation completes
        setTimeout(() => {
            calculatorResult.classList.remove('animate__animated', 'animate__fadeIn');
        }, 1000);
    });

    const translations = {
        en: {
            scholarshipCalculator: "Scholarship Calculator",
            tuitionFee: "Tuition Fee (₹)",
            booksCost: "Books Cost (₹)",
            calculateScholarship: "Calculate Scholarship",
            scholarshipCalculationResult: "Scholarship Calculation Result:",
            totalCost: "Total Cost:",
            scholarshipAmount: "Scholarship Amount:",
            amountToPay: "Amount to Pay:",
            enterValidNumbers: "Please enter valid numbers for both fields."
        },
        hi: {
            scholarshipCalculator: "छात्रवृत्ति कैलकुलेटर",
            tuitionFee: "शिक्षण शुल्क (₹)",
            booksCost: "किताबों की लागत (₹)",
            calculateScholarship: "छात्रवृत्ति की गणना करें",
            scholarshipCalculationResult: "छात्रवृत्ति गणना परिणाम:",
            totalCost: "कुल लागत:",
            scholarshipAmount: "छात्रवृत्ति राशि:",
            amountToPay: "भुगतान करने की राशि:",
            enterValidNumbers: "कृपया दोनों फ़ील्ड के लिए मान्य संख्याएँ दर्ज करें।"
        },
        ta: {
            scholarshipCalculator: "உதவித்தொகை கணிப்பான்",
            tuitionFee: "கல்விக் கட்டணம் (₹)",
            booksCost: "புத்தகங்களின் செலவு (₹)",
            calculateScholarship: "உதவித்தொகையைக் கணக்கிடு",
            scholarshipCalculationResult: "உதவித்தொகை கணக்கீட்டு முடிவு:",
            totalCost: "மொத்த செலவு:",
            scholarshipAmount: "உதவித்தொகை அளவு:",
            amountToPay: "செலுத்த வேண்டிய தொகை:",
            enterValidNumbers: "இரண்டு புலங்களிலும் சரியான எண்களை உள்ளிடவும்."
        }
    };
});
