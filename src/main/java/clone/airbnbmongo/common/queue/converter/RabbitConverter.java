package clone.airbnbmongo.common.queue.converter;

import clone.airbnbmongo.common.exception.CannotConvertRabbitDataException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public record RabbitConverter(ObjectMapper objectMapper) implements QueueConverter {

    @Override
    public <T> T convert(String data, Class<T> classType) {
        try {
            return objectMapper.readValue(data, classType);
        } catch (JsonProcessingException e) {
            throw new CannotConvertRabbitDataException();
        }
    }
}
