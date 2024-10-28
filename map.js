// Create a map instance and set it to Paris coordinates (that will be taken from our database in the beginning)
const map = L.map('map').setView([48.8566, 2.3522], 13); //zoom level = 13

// Add a tile layer (using OpenStreetMap tiles here)
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// Add a marker at the center (Paris)
const marker = L.marker([48.8566, 2.3522]).addTo(map)
    .bindPopup('Paris')
    .openPopup();