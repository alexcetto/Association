/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


setInterval("nextMessage()", 200); //update the chart every 200 ms               

function updateMessages(xhr, status, args) {
    if(!args.ok) return;
    $('#chatContainer').append('<div class="msg">[' +args.dateSent+ '] <strong>'+args.user+'</strong>: '+args.text+'</div>');
}

$("#chatContainer").on("click", "div.msg", function() {
    $("#textSender").val("@" + $(this).children().eq(0).text());
    console.log("oui ?");
});