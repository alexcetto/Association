setInterval("nextMessage()", 200); //update the chart every 200 ms               

function updateMessages(xhr, status, args) {
    if(!args.ok) return;
    $('#chatContainer').prepend('<div class="msg">[' +args.dateSent+ '] <strong>'+args.user+'</strong>: '+args.text+'</div>');
}

$("#chatContainer").on("click", "div.msg", function() {
    $("#textSender").val("@" + $(this).children().eq(0).text());
});

$("#buttonSender").mousedown(function(e) {
    if($("#textSender").val().length == 0
            || $("#userSender").val().length == 0){
        e.preventDefault();
        return false;
    }
});
