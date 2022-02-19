package clone.airbnbmongo.common.accommodation;

import clone.airbnbmongo.accommodation.AccommodationType;
import clone.airbnbmongo.accommodation.api.AccommodationService;
import clone.airbnbmongo.common.BaseTest;
import clone.airbnbmongo.common.queue.dto.AccommodationQueueRes;
import clone.airbnbmongo.common.queue.dto.OptionQueueRes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BaseAccommodationTest extends BaseTest {

    @Autowired
    protected AccommodationService accommodationService;

    @BeforeEach
    void setUp() {
        for(int i = 1; i <= 10; i++) {
            Map<String, List<String>> option = new HashMap<>();

            for(int j = 1; j <= 3; j++)  {
                option.put("category" + i, Arrays.asList("option1", "option2", "option3"));
            }

            AccommodationQueueRes accommodationQueueRes = AccommodationQueueRes.builder()
                    .id((long) i)
                    .name("testName" + i)
                    .description("testDescription" + i)
                    .address("testAddress" + i)
                    .personCount(i + 1)
                    .imagePath(Arrays.asList("testImage1", "testImage2", "testImage3"))
                    .basicPrice(i * 10000)
                    .type(AccommodationType.CONDO)
                    .rating(i)
                    .reviewCount(i * 10)
                    .option(new OptionQueueRes(option))
                    .latitude("testLatitude" + i)
                    .longitude("testLongitude" + i)
                    .build();

            accommodationService.createAccommodation(accommodationQueueRes);
        }
    }
}
