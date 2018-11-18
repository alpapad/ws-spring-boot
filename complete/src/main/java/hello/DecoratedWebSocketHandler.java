package hello;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class DecoratedWebSocketHandler implements WebSocketHandler{
    private final WebSocketHandler handler;
    
    public DecoratedWebSocketHandler(WebSocketHandler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.err.println("afterConnectionEstablished:" + session);
        handler.afterConnectionEstablished(session);
    }
    
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.err.println("handleMessage:" + session);
        handler.handleMessage(session, message);
    }
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.err.println("handleTransportError:" + session);
        exception.printStackTrace();
        handler.handleTransportError(session, exception);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.err.println("afterConnectionClosed:" + session + " ---> " + closeStatus);

        handler.afterConnectionClosed(session, closeStatus);
    }
    
    @Override
    public boolean supportsPartialMessages() {
        return  handler.supportsPartialMessages();
    }
    

}
