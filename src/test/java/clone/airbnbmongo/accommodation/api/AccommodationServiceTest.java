package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.AccommodationType;
import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.Option;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.accommodation.web.dto.AccommodationListRes;
import clone.airbnbmongo.accommodation.web.dto.AccommodationRes;
import clone.airbnbmongo.common.BaseTest;
import clone.airbnbmongo.common.FixProperty;
import clone.airbnbmongo.common.accommodation.BaseAccommodationTest;
import clone.airbnbmongo.common.queue.dto.AccommodationQueueRes;
import clone.airbnbmongo.common.queue.dto.OptionQueueRes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceTest extends BaseTest {

    @Mock
    AccommodationReader ar;

    @Mock
    AccommodationWriter aw;

    @Test
    void loadAccommodation() {
        //given
        int size = 5;
        long id = 1L;
        List<Accommodation> accommodations = createAccommodations(size);

        when(ar.findById(id)).thenReturn(accommodations.get(0));
        AccommodationService as = createAccommodationService();

        //when
        AccommodationRes result = as.loadAccommodation(id);

        //then
        assertThat(result.getId()).isEqualTo(id);
    }

    @Test
    void loadAccommodations() {
        //given
        String addressParam = "test";
        int size = 5;
        PageRequest page = PageRequest.of(0, size);

        List<Accommodation> accommodations = createAccommodations(size);

        when(ar.findPageByAddressContains(addressParam, page)).thenReturn(new PageImpl<>(accommodations));
        AccommodationService as = createAccommodationService();

        //when
        AccommodationListRes res = as.loadAccommodations(addressParam, page);

        //then
        assertThat(res.getTotalCount()).isEqualTo(size);
        assertThat(res.getAccommodationResList().get(0).getAddress()).contains(addressParam);
    }

    @Test
    void createAccommodation()   {
        //given
        AccommodationQueueRes accommodationQueueRes = createInstanceWithFixProperty(AccommodationQueueRes.class, FixProperty.of("id", 1L));
        when(aw.save(accommodationQueueRes.toEntity())).thenReturn(accommodationQueueRes.toEntity());
        AccommodationService as = createAccommodationService();

        //when
        AccommodationRes result = as.createAccommodation(accommodationQueueRes);

        //then
        assertThat(result.getAddress()).isEqualTo(accommodationQueueRes.getAddress());
        assertThat(result.getBasicPrice()).isEqualTo(accommodationQueueRes.getBasicPrice());
        assertThat(result.getLatitude()).isEqualTo(accommodationQueueRes.getLatitude());
        assertThat(result.getImagePath().size()).isEqualTo(accommodationQueueRes.getImagePath().size());
        assertThat(result.getLongitude()).isEqualTo(accommodationQueueRes.getLongitude());
        assertThat(result.getName()).isEqualTo(accommodationQueueRes.getName());
        assertThat(result.getPersonCount()).isEqualTo(accommodationQueueRes.getPersonCount());
        assertThat(result.getRating()).isEqualTo(accommodationQueueRes.getRating());
        assertThat(result.getReviewCount()).isEqualTo(accommodationQueueRes.getReviewCount());
        assertThat(result.getType()).isEqualTo(accommodationQueueRes.getType());
        assertThat(result.getDescription()).isEqualTo(accommodationQueueRes.getDescription());
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

    private AccommodationService createAccommodationService() {
        return new AccommodationService(aw, ar);
    }

    private AccommodationQueueRes createAccommodationQueueRes() {
        return AccommodationQueueRes.builder()
                .id(1L)
                .name()
        .description()
        .address()
        .personCount()
        .imagePath()
        .basicPrice()
        .type()
        .rating()
        .reviewCount()
        .option()
        .latitude()
        .longitude();
    }
}