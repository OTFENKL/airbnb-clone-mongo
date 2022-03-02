package clone.airbnbmongo.common.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

//    @RabbitListener(queues = {"airbnb-clone"})
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}