// JavaScript Libraries
document.write('<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>');
document.write('<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>');
document.write('<script src="lib/wow/wow.min.js"></script>');
document.write('<script src="lib/easing/easing.min.js"></script>');
document.write('<script src="lib/waypoints/waypoints.min.js"></script>');
document.write('<script src="lib/counterup/counterup.min.js"></script>');
document.write('<script src="lib/owlcarousel/owl.carousel.min.js"></script>');
document.write('<script src="lib/isotope/isotope.pkgd.min.js"></script>');
document.write('<script src="lib/lightbox/js/lightbox.min.js"></script>');

// Custom JavaScript
$(document).ready(function () {
    // Smooth scrolling
    $('a[href*="#"]:not([href="#"])').click(function () {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var target = $(this.hash);
            target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
            if (target.length) {
                $('html, body').animate({
                    scrollTop: target.offset().top
                }, 1000, 'easeInOutExpo');
                return false;
            }
        }
    });

    // Back to top button
    var backToTop = $('.back-to-top');
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            backToTop.fadeIn('slow');
        } else {
            backToTop.fadeOut('slow');
        }
    });
    backToTop.click(function () {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
    });

    // Counter up
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });

    // Initiate WOW.js
    new WOW().init();

    // Owl Carousel for testimonials
    $('.owl-carousel').owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        loop: true,
        nav: false,
        dots: true
    });

    // Isotope for filtering projects
    var $grid = $('.isotope-container').isotope({
        itemSelector: '.isotope-item',
        layoutMode: 'fitRows'
    });
    $('.isotope-filter li').on('click', function () {
        $(".isotope-filter li").removeClass('filter-active');
        $(this).addClass('filter-active');
        $grid.isotope({ filter: $(this).data('filter') });
    });
});
