package clone.airbnbmongo.accommodation.web.controller;

import clone.airbnbmongo.accommodation.AccommodationType;
import clone.airbnbmongo.accommodation.api.AccommodationService;
import clone.airbnbmongo.common.queue.dto.AccommodationQueueRes;
import clone.airbnbmongo.common.queue.dto.OptionQueueRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AccommodationController {

    private final AccommodationService accommodationService;

    @PostConstruct
    public void initData()  {
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

    @GetMapping("/accommodations")
    public ResponseEntity<?> loadAccommodations(@RequestParam("address") String address, Pageable pageable) {
        return accommodationService.loadAccommodations(address, pageable);
    }

    @GetMapping("/accommodations/{accommodationId}")
    public ResponseEntity<?> loadAccommodation(@PathVariable long accommodationId) {
        return accommodationService.loadAccommodation(accommodationId);
    }
}
