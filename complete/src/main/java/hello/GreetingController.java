package hello;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
    Logger log = LoggerFactory.getLogger(GreetingController.class);

    @MessageMapping("/hello")
    @SendTo("/topic/test")
    public Greeting greeting(HelloMessage message) throws Exception {
        log.info("Received hello: {}", message.getName() + " for topic");
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
