package com.len.lenulibrary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * PIC : Muhamad Naufal Al Ghani
 * Tanggal_Dibuat : 15/05/2024
 * Tujuan : Konfigurasi WebSocket untuk mengaktifkan dan mengatur penggunaan
 *          message broker dan endpoint stomp.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
    
    // Konfigurasi message broker untuk mengatur prefix broker yang akan digunakan
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); 
        config.setApplicationDestinationPrefixes("/app"); 
    }

    // Registrasi endpoint Stomp yang akan digunakan untuk koneksi WebSocket
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-notifications").withSockJS(); // Endpoint untuk koneksi WebSocket
    }
}

