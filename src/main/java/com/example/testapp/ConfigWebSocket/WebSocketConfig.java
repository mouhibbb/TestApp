package com.example.testapp.ConfigWebSocket;

import com.example.testapp.Controller.UrlWebSocketController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final UrlWebSocketController urlWebSocketController;

    public WebSocketConfig(UrlWebSocketController urlWebSocketController) {
        this.urlWebSocketController = urlWebSocketController;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(urlWebSocketController, "/ws/api").setAllowedOrigins("*");
    }
}
