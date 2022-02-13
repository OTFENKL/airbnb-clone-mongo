package clone.airbnbmongo.common.queue.converter;

public interface QueueConverter {

    <T> T convert(String data, Class<T> classType);
}
