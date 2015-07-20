package com.byteknowledge.mytiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@ComponentScan("com.byteknowledge.mytiles")
@PropertySource("classpath:/websocket.properties")
@EnableWebSocketMessageBroker
public class MyTilesWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    //private @Value("${redis.host-name}") String redisHostName;
    //private @Value("${redis.port}") int redisPort;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/mytiles")
            .setAllowedOrigins("*") // TODO[fcarta] for now allow all - needed since seperate apps cause CORS errors
            .withSockJS();
    }
}
