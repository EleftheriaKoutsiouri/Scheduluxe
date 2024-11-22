    function toggleDropdown() {
        var content = document.querySelector('.dropdown-content');
        content.style.display = content.style.display === "block" ? "none" : "block";
    }
    window.onclick = function(event) {
        var dropdownContent = document.querySelector('.dropdown-content');
        var dropdownButton = document.querySelector('.dropdown button');
        if (!dropdownButton.contains(event.target) && !dropdownContent.contains(event.target)) {
            dropdownContent.style.display = "none";  
        }
    };