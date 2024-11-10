document.addEventListener('DOMContentLoaded', () => {
    const menuToggle = document.querySelector('.menu-toggle');
    const navLinks = document.querySelector('.nav-links');

    // Initialize the menu state based on the screen width
    let isMenuOpen = window.innerWidth > 768;

    // Set initial display for nav links based on screen width
    navLinks.style.display = isMenuOpen ? 'flex' : 'none';

    // Function to handle hamburger menu toggle
    menuToggle.addEventListener('click', () => {
        isMenuOpen = !isMenuOpen;
        navLinks.style.display = isMenuOpen ? 'flex' : 'none';
    });

    // Adjust menu display on window resize
    window.addEventListener('resize', () => {
        if (window.innerWidth > 768) {
            navLinks.style.display = 'flex'; // Show the menu on larger screens
            isMenuOpen = true; // Reset the menu state
        } else {
            navLinks.style.display = 'none'; // Hide the menu on smaller screens
            isMenuOpen = false; // Ensure menu starts hidden on smaller screens
        }
    });
});
