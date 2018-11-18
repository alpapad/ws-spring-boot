package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic")//
                .setTaskScheduler(new ConcurrentTaskScheduler())//
                .setHeartbeatValue(new long[] { 1000, 1000 });
        
        config.setApplicationDestinationPrefixes("/app");
    }
    
    /**
     * Configure the {@link org.springframework.messaging.MessageChannel} used for
     * incoming messages from WebSocket clients. By default the channel is backed by
     * a thread pool of size 1. It is recommended to customize thread pool settings
     * for production use.
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        ThreadPoolTaskExecutor exc = new ThreadPoolTaskExecutor();
        exc.setCorePoolSize(10);
        registration.taskExecutor(exc);
    }
    
    /**
     * Configure the {@link org.springframework.messaging.MessageChannel} used for
     * outbound messages to WebSocket clients. By default the channel is backed by a
     * thread pool of size 1. It is recommended to customize thread pool settings
     * for production use.
     */
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        ThreadPoolTaskExecutor exc = new ThreadPoolTaskExecutor();
        exc.setCorePoolSize(10);
        registration.taskExecutor(exc);
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/hello");
    }
}
