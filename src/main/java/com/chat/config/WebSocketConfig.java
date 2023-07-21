package com.chat.config;

import com.chat.handler.ChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/* WebSocket 설정 */
@Configuration
@RequiredArgsConstructor
@EnableWebSocket // 웹소켓 활성화
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatHandler chatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 웹소켓 접속을 위한 엔드포인트 등록
        registry.addHandler(chatHandler, "/ws/chat").setAllowedOrigins("*");
    }
}
