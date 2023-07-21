$(document).ready(function() {
    const sockJs = new SockJS("/ws/chat");
    const stomp = Stomp.over(sockJs);

    stomp.connect({}, function() {
        console.log("Connected");

        stomp.subscribe("/topic/chat", function (chat) {
            appendMessage(chat.body);
        })

        // 전송 버튼을 누르면 /app destination으로 메시지를 보낸다.
        // /app 경로에서는 해당 메시지를 받고 구독하고 있는 사람들에게 뿌려준다.
        $('#msg_send_btn').click(function () {
            let msgContentInput = $('#msg_content');
            stomp.send('/app/send', {}, msgContentInput.val());
            msgContentInput.val('');
        });
    })
})

function appendMessage(msg) {
    console.log("Msg:", msg);
    $('#msg_history').append(
        $('<div>').addClass('incoming_msg').append(
            $('<div>').addClass('received_msg').append(
                $('<div>').addClass('received_withd_msg').append(
                    $('<p>').text(msg)
                )
            )
        )
    );
}