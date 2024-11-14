<script>
    document.querySelector('.download-btn').addEventListener('click', function() {
        const element = document.querySelector('.schedule-container');
        
        // Επιλογές για τη βιβλιοθήκη html2pdf
        const options = {
            margin:       0.5,      // Περιθώριο στο PDF (σε ίντσες)
            filename:     'schedule.pdf',  // Όνομα αρχείου
            image:        { type: 'jpeg', quality: 0.98 },
            html2canvas:  { scale: 2 },    // Κλίμακα για καλύτερη ανάλυση
            jsPDF:        { unit: 'in', format: 'a4', orientation: 'portrait' }
        };
        
        // Δημιουργία του PDF
        html2pdf().set(options).from(element).save();
    });
</script>
