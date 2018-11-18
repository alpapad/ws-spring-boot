package hello;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;

@Component
public class SmartApplicationListenerImpl implements ApplicationListener<AbstractSubProtocolEvent> {
    
    @Override
    public void onApplicationEvent(AbstractSubProtocolEvent event) {
        System.err.println("AbstractSubProtocolEvent ...." + event);
    }
}
