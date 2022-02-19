package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.common.BaseTest;
import clone.airbnbmongo.common.FixProperty;
import clone.airbnbmongo.common.queue.dto.AccommodationQueueRes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AccommodationServiceTest extends BaseTest {

//    @MockBean
//    AccommodationRepository mockAccommodationRepository;

    @Autowired
    AccommodationRepository accommodationRepository;

    @Autowired
    AccommodationService accommodationService;

    @Test
    public void createAccommodation()   {
        AccommodationQueueRes accommodationQueueRes = createInstanceWithFixProperty(AccommodationQueueRes.class, new FixProperty("id", 1L));
        System.out.println(accommodationQueueRes);
        Accommodation accommodation = accommodationQueueRes.toEntity();

        accommodationService.createAccommodation(accommodationQueueRes);
        Accommodation findAccommodation = accommodationRepository.findById(accommodation.getId()).get();

        assertThat(accommodation.getAddress()).isEqualTo(findAccommodation.getAddress());
        assertThat(accommodation.getBasicPrice()).isEqualTo(findAccommodation.getBasicPrice());
        assertThat(accommodation.getLatitude()).isEqualTo(findAccommodation.getLatitude());
        assertThat(accommodation.getImagePath().size()).isEqualTo(findAccommodation.getImagePath().size());
        assertThat(accommodation.getLongitude()).isEqualTo(findAccommodation.getLongitude());
        assertThat(accommodation.getName()).isEqualTo(findAccommodation.getName());
//        assertThat(accommodation.getOptions().size()).isEqualTo(findAccommodation.getOptions().size());
        assertThat(accommodation.getPersonCount()).isEqualTo(findAccommodation.getPersonCount());
        assertThat(accommodation.getRating()).isEqualTo(findAccommodation.getRating());
        assertThat(accommodation.getReviewCount()).isEqualTo(findAccommodation.getReviewCount());
        assertThat(accommodation.getType()).isEqualTo(findAccommodation.getType());
        assertThat(accommodation.getDescription()).isEqualTo(findAccommodation.getDescription());
    }


}