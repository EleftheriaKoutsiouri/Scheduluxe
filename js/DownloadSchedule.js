document.querySelector('.download-btn').addEventListener('click', function() {
    const element = document.querySelector('.schedule-container');
    const options = {
        margin:       1,
        filename:     'schedule.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 2, width: element.offsetWidth },
        jsPDF:        { unit: 'in', format: 'a4', orientation: 'landscape' }
    };
    html2pdf().set(options).from(element).save();
});
