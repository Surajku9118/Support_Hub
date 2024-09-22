        // JavaScript for form validation and submission
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                var form = document.getElementById('scholarshipApplication');
                form.addEventListener('submit', function(e) {
                    e.preventDefault();
                    if (form.checkValidity() === false) {
                        e.stopPropagation();
                    } else {
                        // Simulate form submission
                        alert('Thank you for your application. We will review it and get back to you soon.');
                        form.reset();
                    }
                    form.classList.add('was-validated');
                }, false);
            }, false);
        })();
        // Function to validate the application form
function validateForm(form) {
    let isValid = true;
    const requiredFields = form.querySelectorAll('[required]');

    requiredFields.forEach(field => {
        if (!field.value.trim()) {
            isValid = false;
            field.classList.add('is-invalid');
        } else {
            field.classList.remove('is-invalid');
        }
    });

    return isValid;
}

// Update the form submission event listener
const scholarshipApplication = document.getElementById('scholarshipApplication');

if (scholarshipApplication) {
    scholarshipApplication.addEventListener('submit', function(e) {
        e.preventDefault();
        if (validateForm(this)) {
            alert('Thank you for your application. We will review it and get back to you soon.');
            scholarshipApplication.reset();
        } else {
            alert('Please fill in all required fields before submitting.');
        }
    });
}

// Add smooth scrolling for all anchor links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
            behavior: 'smooth'
        });
    });
});

// Initialize tooltips
var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
});
  