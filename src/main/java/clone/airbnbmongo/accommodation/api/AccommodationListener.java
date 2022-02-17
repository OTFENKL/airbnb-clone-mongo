package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.common.queue.converter.QueueConverter;
import clone.airbnbmongo.common.queue.dto.AccommodationQueueRes;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccommodationListener {

    private final AccommodationService accommodationService;
    private final QueueConverter queueConverter;

    @RabbitListener(queues = {"airbnb-clone"})
    public <T> void receiveAccommodation(String data)  {
        AccommodationQueueRes accommodationQueueRes = queueConverter.convert(data, AccommodationQueueRes.class);
        accommodationService.createAccommodation(accommodationQueueRes);
    }
}
