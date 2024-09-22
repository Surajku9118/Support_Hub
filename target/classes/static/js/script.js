// // JavaScript Libraries
// document.write('<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>');
document.write('<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>');
document.write('<script src="lib/wow/wow.min.js"></script>');
document.write('<script src="lib/easing/easing.min.js"></script>');
document.write('<script src="lib/waypoints/waypoints.min.js"></script>');
document.write('<script src="lib/counterup/counterup.min.js"></script>');
document.write('<script src="lib/owlcarousel/owl.carousel.min.js"></script>');
document.write('<script src="lib/isotope/isotope.pkgd.min.js"></script>');

// document.write('<script src="lib/lightbox/js/lightbox.min.js"></script>');

// Start of JavaScript Coding...

AOS.init({
            duration: 1000, // Animation duration in milliseconds
        });
// Pages

$(document).ready(function () {
    $('.slick-slider').slick({
        dots: true,
        infinite: true,
        speed: 300,
        slidesToShow: 3,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });
});
//news and Update
document.addEventListener('DOMContentLoaded', function () {
    const articlesContainer = document.getElementById('articles-container');
    const categoryFilter = document.getElementById('category-filter');
    const newsletterForm = document.getElementById('newsletter-form');

    // Sample news articles (in a real application, this would come from a server)
    const newsArticles = [
        {
            title: "Family Support Program Helps 100 Children Attend College",
            category: "success-stories",
            date: "2024-07-10",
            content: "Our Family Support Program has successfully helped 100 children of fallen service members attend college this year..."
        },
        {
            title: "Recent Donations Fund New Counseling Center",
            category: "donation-impact",
            date: "2024-06-28",
            content: "Thanks to your generous donations, we've opened a new counseling center to provide mental health support to military families..."
        },
        {
            title: "Annual Remembrance Ceremony Next Month",
            category: "events",
            date: "2024-06-15",
            content: "Join us next month for our Annual Remembrance Ceremony honoring fallen service members and their families..."
        },
        {
            title: "New Policy Expands Education Benefits for Spouses",
            category: "policy-updates",
            date: "2024-05-30",
            content: "A new policy has been implemented to expand education benefits for spouses of fallen service members..."
        }
    ];

    // Display all articles initially
    displayArticles(newsArticles);

    // Filter articles when category is changed
    categoryFilter.addEventListener('change', function () {
        const selectedCategory = this.value;
        const filteredArticles = selectedCategory === 'all'
            ? newsArticles
            : newsArticles.filter(article => article.category === selectedCategory);
        displayArticles(filteredArticles);
    });

    // Handle newsletter signup
    newsletterForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const email = this.querySelector('input[type="email"]').value;
        alert(`Thank you for subscribing with email: ${email}`);
        this.reset();
    });

    function displayArticles(articles) {
        articlesContainer.innerHTML = '';
        articles.forEach(article => {
            const articleElement = document.createElement('div');
            articleElement.className = 'col-md-6 mb-4';
            articleElement.innerHTML = `
                <div class="card h-100" data-aos="zoom-in-right" data-aos-delay="100" data-aos-duration="1000">
                    <div class="card-body">
                        <h5 class="card-title" style="font-size:30px">${article.title}</h5>
                        <h6 class="card-subtitle mb-2 text-muted"style="font-size:15px">${article.category} | ${article.date}</h6>
                        <marquee><img src="images/new_gif.gif" style="hight:6px; width:30px"><p class="card-text">${article.content}</p></marquee>
                        <a href="#" class="btn btn-primary" style="font-size:15px">Read More</a>
                    </div>
                </div>
            `;
            articlesContainer.appendChild(articleElement);
        });
    }
});




//log out start
document.addEventListener('DOMContentLoaded', function () {
    const logoutButton = document.getElementById('logoutButton');
    if (logoutButton) {
        logoutButton.addEventListener('click', function () {
            localStorage.removeItem('loggedInUser');
            showRegisterForm();
        });
    }
});

//log out end


// End of Pages

// Navigation
const dropdownItems = document.querySelectorAll('.dropdown-hover')

if (window.innerWidth < 1000) {
    const menuIcon = document.querySelector('.menu')
    const navbar = document.querySelector('.navbar')

    menuIcon.addEventListener('click', () => {
        navbar.classList.toggle('change')

        if (!navbar.classList.contains('change')) {
            document.querySelectorAll('.nav-dropdown').forEach(dropdown => {
                dropdown.style.left = '20rem'
            })
        }
    })

    document.querySelectorAll('.show-dropdown').forEach(link => {
        link.addEventListener('click', () => {
            link.nextElementSibling.style.left = '0'
        })
    })

    document.querySelectorAll('.dropdown-heading-link').forEach(headingLink => {
        headingLink.addEventListener('click', () => {
            headingLink.parentElement.parentElement.style.left = '20rem'
        })
    })
} else {
    dropdownItems.forEach(dropdownItem => {
        dropdownItem.addEventListener('mouseover', () => {
            dropdownItem.lastElementChild.style.cssText = 'opacity: 1; visibility: visible'
            //document.querySelector('.navbar-wrapper').style.background = 'linear-gradient(to right, #066399, #2f8fdf, #066399)'
            dropdownItem.firstElementChild.firstElementChild.style.transform = 'rotate(180deg)'
        })
        dropdownItem.addEventListener('mouseout', () => {
            dropdownItem.lastElementChild.style.cssText = 'opacity: 0; visibility: hidden'
            //document.querySelector('.navbar-wrapper').style.background = 'none'
            dropdownItem.firstElementChild.firstElementChild.style.transform = 'rotate(0)'
        })
    })
}


window.addEventListener('resize', () => {
    window.location.reload()
});
//video link
document.querySelector('.play-btn').addEventListener('click', function (event) {
    event.preventDefault(); // Prevent the default anchor behavior
    var video = document.getElementById('myVideo');
    video.style.display = 'block'; // Show the video
    video.play(); // Play the video
    this.style.display = 'none'; // Hide the play button
    document.querySelector('.close-btn').style.display = 'block'; // Show the close button


    document.querySelector('.close-btn').addEventListener('click', function (event) {
        event.preventDefault(); // Prevent the default anchor behavior
        var video = document.getElementById('myVideo');
        video.pause(); // Stop the video
        video.currentTime = 0; // Reset video to start
        video.style.display = 'none'; // Hide the video
        this.style.display = 'none'; // Hide the close button
        document.querySelector('.play-btn').style.display = 'block'; // Show the play button

        // Refresh the page after a short delay

    });
    setTimeout(function () {
        location.reload();
    }, 49000); // 1000 milliseconds = 1 second
});
//video link
function showParagraph() {
    var paragraph = document.getElementById('info');
    if (paragraph.classList.contains('hidden')) {
        paragraph.classList.remove('hidden');
    }
}
window.sr = ScrollReveal();
sr.reveal('banner',{duration:1000});


// Show alert message if the "alertMessage" parameter is present
        window.onload = function() {
            var alertMessage = /*[[${alertMessage}]]*/ '';
            if (alertMessage) {
                alert(alertMessage);
            }
        };


// // End of Navigation

const slider = document.querySelector('.slider');
        const slides = document.querySelectorAll('.slide');
        let currentSlide = 0;

        function goToSlide(index) {
            slider.style.transform = `translateX(-${index * 100}%)`;
            currentSlide = index;
        }

        function nextSlide() {
            currentSlide = (currentSlide + 1) % slides.length;
            goToSlide(currentSlide);
        }

        // Auto-slide every 5 seconds
        setInterval(nextSlide, 5000);
// // END of JavaScript Coding...

