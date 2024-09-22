document.addEventListener('DOMContentLoaded', function() {
    const applyBtn = document.getElementById('applyBtn');
    const applicationForm = document.getElementById('applicationForm');
    const scholarshipApplication = document.getElementById('scholarshipApplication');
    const contactForm = document.getElementById('contactForm');
    
    const backToTopButton = document.createElement('button');

    // Back to Top button
    backToTopButton.id = 'backToTop';
    backToTopButton.className = 'btn btn-primary btn-sm';
    backToTopButton.innerHTML = '<i class="fas fa-arrow-up"></i>';
    document.body.appendChild(backToTopButton);

    backToTopButton.addEventListener('click', () => {
        window.scrollTo({ top: 0, behavior: 'smooth' });
    });

    window.addEventListener('scroll', () => {
        backToTopButton.style.display = window.pageYOffset > 300 ? 'block' : 'none';
    });

    // Application form toggle
    if (applyBtn) {
        applyBtn.addEventListener('click', function() {
            applicationForm.classList.remove('d-none');
            applicationForm.scrollIntoView({ behavior: 'smooth' });
        });
    }

    // Application form submission
    if (scholarshipApplication) {
        scholarshipApplication.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Thank you for your application. We will review it and get back to you soon.');
            scholarshipApplication.reset();
            applicationForm.classList.add('d-none');
        });
    }

    // Contact form submission
    if (contactForm) {
        contactForm.addEventListener('submit', function(e) {
            e.preventDefault();
            alert('Thank you for your message. We will respond as soon as possible.');
            contactForm.reset();
        });
    }

    // Smooth scrolling for navigation links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            e.preventDefault();
            document.querySelector(this.getAttribute('href')).scrollIntoView({ behavior: 'smooth' });
        });
    });

    // Dark mode toggle
const darkModeToggle = document.getElementById('darkModeToggle');
const body = document.body;

darkModeToggle.addEventListener('click', () => {
    body.classList.toggle('dark-mode');
    const icon = darkModeToggle.querySelector('i');
    icon.classList.toggle('bi-moon-stars');
    icon.classList.toggle('bi-sun');
});


// Scholarship calculator
const calculatorForm = document.getElementById('calculatorForm');
const calculatorResult = document.getElementById('calculatorResult');

calculatorForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const tuitionFee = parseInt(document.getElementById('tuitionFee').value);
    const booksCost = parseInt(document.getElementById('booksCost').value);
    
    let scholarship = tuitionFee + Math.min(booksCost, 5000);
    scholarship = Math.min(scholarship, 25000);
    
    calculatorResult.innerHTML = `
        <div class="alert alert-success">
            Your estimated scholarship amount: ₹${scholarship.toLocaleString()}
        </div>
    `;
});

// Language selector
const languageDropdown = document.getElementById('languageDropdown');
const languageItems = document.querySelectorAll('[data-lang]');

languageItems.forEach(item => {
    item.addEventListener('click', (e) => {
        e.preventDefault();
        const lang = e.target.getAttribute('data-lang');
        // Here you would typically load language-specific content
        // For demonstration, we'll just update the button text
        languageDropdown.textContent = e.target.textContent;
    });
});

// Basic translations (expand this object with more translations as needed)
const translations = {
    en: {
        title: "Army Welfare Corpus Education Scholarship",
        learnMore: "Learn More",
        apply: "Apply Now"
    },
    hi: {
        title: "सेना कल्याण कोष शिक्षा छात्रवृत्ति",
        learnMore: "और जानें",
        apply: "अभी आवेदन करें"
    },
    ta: {
        title: "ராணுவ நல நிதி கல்வி உதவித்தொகை",
        learnMore: "மேலும் அறிக",
        apply: "இப்போது விண்ணப்பிக்கவும்"
    }
};

function updateLanguage(lang) {
    document.querySelectorAll('[data-translate]').forEach(element => {
        const key = element.getAttribute('data-translate');
        if (translations[lang] && translations[lang][key]) {
            element.textContent = translations[lang][key];
        }
    });
}


    // Eligibility Checker
    const eligibilityForm = document.getElementById('eligibilityForm');
    const eligibilityResult = document.getElementById('eligibilityResult');

    if (eligibilityForm) {
        eligibilityForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const relationship = document.getElementById('relationship').value;
            const course = document.getElementById('course').value;

            if (relationship && course === 'professional') {
                eligibilityResult.innerHTML = '<div class="alert alert-success">You are eligible for the Education Scholarship!</div>';
            } else {
                eligibilityResult.innerHTML = '<div class="alert alert-danger">Sorry, you are not eligible for the Education Scholarship. It is only available for professional courses for immediate family members of service personnel.</div>';
            }
        });
    }

    // Search Functionality
    const searchForm = document.getElementById('searchForm');
    const searchInput = document.getElementById('searchInput');
    const searchResults = document.getElementById('searchResults');

    const content = {
        'scholarship': 'The Education Scholarship covers tuition fees plus Rs 5,000/- for books, subject to a maximum of Rs 25,000/- per annum.',
        'penury': 'The Penury Grant is provided at the rate of Rs. 4000/- per month for life time, effective from 01 Apr 2017.',
        'self-employment': 'A grant of Rs 25,000/- is available for self-employment or agro-based ventures.',
        'eligibility': 'The Education Scholarship is available for children, spouses, and siblings of service members who are pursuing professional courses.',
        'application': 'To apply, fill out the application form with accurate information and upload all required documents.',
    };

    if (searchForm) {
        searchForm.addEventListener('submit', function(e) {
            e.preventDefault();
            const query = searchInput.value.toLowerCase();
            let results = '';

            for (const [key, value] of Object.entries(content)) {
                if (key.includes(query) || value.toLowerCase().includes(query)) {
                    results += `<p><strong>${key.charAt(0).toUpperCase() + key.slice(1)}:</strong> ${value}</p>`;
                }
            }

            searchResults.innerHTML = results || '<p>No results found. Please try different keywords.</p>';
        });
    }

    // Lazy loading images
    const lazyImages = [].slice.call(document.querySelectorAll('img[loading="lazy"]'));

    if ('IntersectionObserver' in window) {
        const lazyImageObserver = new IntersectionObserver(function(entries, observer) {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    const lazyImage = entry.target;
                    lazyImage.src = lazyImage.dataset.src;
                    lazyImage.classList.remove('lazy');
                    lazyImageObserver.unobserve(lazyImage);
                }
            });
        });

        lazyImages.forEach(lazyImage => {
            lazyImageObserver.observe(lazyImage);
        });
    } else {
        // Fallback for browsers that don't support IntersectionObserver
        const lazyLoad = function() {
            lazyImages.forEach(lazyImage => {
                if ((lazyImage.getBoundingClientRect().top <= window.innerHeight && lazyImage.getBoundingClientRect().bottom >= 0) && getComputedStyle(lazyImage).display !== 'none') {
                    lazyImage.src = lazyImage.dataset.src;
                    lazyImage.classList.remove('lazy');

                    lazyImages.splice(lazyImages.indexOf(lazyImage), 1);

                    if (lazyImages.length === 0) {
                        document.removeEventListener('scroll', lazyLoad);
                        window.removeEventListener('resize', lazyLoad);
                        window.removeEventListener('orientationchange', lazyLoad);
                    }
                }
            });
        };

        document.addEventListener('scroll', lazyLoad);
        window.addEventListener('resize', lazyLoad);
        window.addEventListener('orientationchange', lazyLoad);
    }
});