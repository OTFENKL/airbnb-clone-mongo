package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.web.dto.AccommodationRes;
import clone.airbnbmongo.common.queue.converter.QueueConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccommodationListener {

    private final AccommodationService accommodationService;
    private final QueueConverter queueConverter;

    @RabbitListener(queues = "{airbnb-clone}")
    public <T> void receiveAccommodation(String data)  {
        AccommodationRes accommodationRes = queueConverter.convert(data, AccommodationRes.class);
        accommodationService.createAccommodation(accommodationRes);
    }

}
