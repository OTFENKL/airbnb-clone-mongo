package clone.airbnbmongo.common.config;

import clone.airbnbmongo.accommodation.web.AccommodationRes;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @RabbitListener(queues = {"airbnb-clone"})
    public void receiveMessage(AccommodationRes message) {
        System.out.println("Received <" + message + ">");
    }
}