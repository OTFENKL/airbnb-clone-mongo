package clone.airbnbmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AirbnbMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirbnbMongoApplication.class, args);
    }
}
