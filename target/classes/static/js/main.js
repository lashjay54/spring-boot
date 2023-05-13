$(document).ready(function() {
    $( "#dateOfBirth" ).datepicker({
        dateFormat: 'dd/mm/yy'
    });
});

function closeModal() {
    $('#inboxModal').hide();
}