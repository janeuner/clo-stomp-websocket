function moveGreen() {
    $('#move').click(function() {
        $('#study').html($('#study').html()+'<img src="img/green.png">');
    });
}


$(document).ready(function() {
    moveGreen();
});
