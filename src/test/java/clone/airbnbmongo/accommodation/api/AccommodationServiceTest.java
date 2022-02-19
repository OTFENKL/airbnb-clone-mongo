package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.AccommodationType;
import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.Option;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.accommodation.web.dto.AccommodationListRes;
import clone.airbnbmongo.common.FixProperty;
import clone.airbnbmongo.common.accommodation.BaseAccommodationTest;
import clone.airbnbmongo.common.queue.dto.AccommodationQueueRes;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class AccommodationServiceTest extends BaseAccommodationTest {

    @Mock
    AccommodationRepository accommodationRepository;

    @Test
    void loadAccommodation() {

    }

    @Test
    void loadAccommodations() {
        //given
        String addressParam = "test";
        PageRequest page = PageRequest.of(0, 5);
        int size = 5;

        List<Accommodation> accommodations = createAccommodations(size);

        AccommodationReader ar = new AccommodationReader(accommodationRepository);
        AccommodationWriter aw = new AccommodationWriter(accommodationRepository);
        
        when(ar.findPageByAddressContains(addressParam, page)).thenReturn(new PageImpl<>(accommodations));
        AccommodationService as = new AccommodationService(aw, ar);

        //when
        AccommodationListRes res = as.loadAccommodations(addressParam, page);

        //then
        assertThat(res.getTotalCount()).isEqualTo(size);
        assertThat(res.getAccommodationResList().get(0).getAddress()).contains(addressParam);
    }

    @Test
    void createAccommodation()   {
        AccommodationQueueRes accommodationQueueRes = createInstanceWithFixProperty(AccommodationQueueRes.class, FixProperty.of("id", 1L));
        Accommodation accommodation = accommodationQueueRes.toEntity();

        accommodationService.createAccommodation(accommodationQueueRes);
        Accommodation findAccommodation = accommodationRepository.findById(accommodation.getId()).get();

        assertThat(accommodation.getAddress()).isEqualTo(findAccommodation.getAddress());
        assertThat(accommodation.getBasicPrice()).isEqualTo(findAccommodation.getBasicPrice());
        assertThat(accommodation.getLatitude()).isEqualTo(findAccommodation.getLatitude());
        assertThat(accommodation.getImagePath().size()).isEqualTo(findAccommodation.getImagePath().size());
        assertThat(accommodation.getLongitude()).isEqualTo(findAccommodation.getLongitude());
        assertThat(accommodation.getName()).isEqualTo(findAccommodation.getName());
        assertThat(accommodation.getPersonCount()).isEqualTo(findAccommodation.getPersonCount());
        assertThat(accommodation.getRating()).isEqualTo(findAccommodation.getRating());
        assertThat(accommodation.getReviewCount()).isEqualTo(findAccommodation.getReviewCount());
        assertThat(accommodation.getType()).isEqualTo(findAccommodation.getType());
        assertThat(accommodation.getDescription()).isEqualTo(findAccommodation.getDescription());
    }

    private List<Accommodation> createAccommodations(int size) {
        List<Accommodation> accommodations = new ArrayList<>();

        for(int i = 1; i <= size; i++) {
            Map<String, List<String>> option = new HashMap<>();

            for(int j = 1; j <= 3; j++)  {
                option.put("category" + i, Arrays.asList("option1", "option2", "option3"));
            }

            Accommodation accommodation = Accommodation.builder()
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
                    .option(new Option(option))
                    .latitude("testLatitude" + i)
                    .longitude("testLongitude" + i)
                    .build();

            accommodations.add(accommodation);
        }

        return accommodations;
    }
}