package client;


import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import hello.Greeting;

public class MySessionHandler extends StompSessionHandlerAdapter {
    Logger log = LoggerFactory.getLogger(MySessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.setAutoReceipt(true);
        session.subscribe("/topic/test", this);
        session.send("/app/hello", "{\"name\":\"Client\"}".getBytes());

        log.info("New session: {}", session.getSessionId());
        
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;
    }
    /**
     * This implementation is empty.
     */
    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.err.println(session);
        System.err.println(session.isConnected());
        exception.printStackTrace();
    }
    
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Received: {}", ((Greeting) payload).getContent());
    }
}
