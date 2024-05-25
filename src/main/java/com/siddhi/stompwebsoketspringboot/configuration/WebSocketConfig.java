package com.siddhi.stompwebsoketspringboot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp-endpoint")
                .setHandshakeHandler(new UserHandshakeHandler());

        registry.addEndpoint("/stomp-endpoint")
                .setHandshakeHandler(new UserHandshakeHandler())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
         registry.enableSimpleBroker("/topic");
         registry.setApplicationDestinationPrefixes("/ws");
        // registry.setUserDestinationPrefix("/user");
    }
}
