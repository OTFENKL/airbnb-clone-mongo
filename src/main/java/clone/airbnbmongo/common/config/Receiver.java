package clone.airbnbmongo.common.config;

import org.springframework.stereotype.Component;
import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(TestDto testDto) {
        System.out.println("Received <" + testDto + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
