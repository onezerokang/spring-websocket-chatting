package com.chat.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

/* 클라이언트가 보낸 메시지를 처리할 핸들러 */
@Component
public class ChatHandler extends TextWebSocketHandler {
    /* 웹소켓에서의 세션은 연결 정보를 의미한다 */
    private static List<WebSocketSession> list = new ArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("payload = " + payload);

        for(WebSocketSession sess: list) {
            // 메시지를 웹소켓 연결 중인 사람들에게 보낸다.
            sess.sendMessage(message);
        }
    }

    /* Client 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        list.add(session);
        System.out.println("session = " + " 클라이언트 접속");
    }


    /* Client 접속 해제 시 호출되는 메서드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("session = " + " 클라이언트 접속 해제");
        list.remove(session);
    }
}
