package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.AccommodationType;
import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.Option;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.common.exception.AccommodationNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccommodationReaderTest {

    @Mock
    AccommodationRepository mockAccommodationRepository;

    private AccommodationReader ar;

    @BeforeEach
    public void init()  {
        ar = new AccommodationReader(mockAccommodationRepository);
    }

    @Test
    public void findById()  {
        // given
        int size = 1;
        List<Accommodation> accommodationList = createAccommodation(size);
        long accommodationId = 1L;
        when(mockAccommodationRepository.findById(accommodationId)).thenReturn(Optional.of(accommodationList.get(0)));

        // when
        Accommodation accommodation = ar.findById(accommodationId);

        // then
        assertThat(accommodation.getId()).isEqualTo(accommodationId);
    }

    @Test
    public void findByIdFailedByNonExistId()    {
        // given
        long accommodationId = -1L;
        when(mockAccommodationRepository.findById(accommodationId)).thenThrow(new AccommodationNotFoundException());

        // when & then
        assertThrows(AccommodationNotFoundException.class, () -> ar.findById(accommodationId));
    }

    @Test
    public void findPageByAddressContains() {
        // given
        String address = "testAddress";
        int pageNumber = 0;
        int size = 5;
        List<Accommodation> accommodationList = createAccommodation(size);
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        when(ar.findPageByAddressContains(address, pageRequest)).thenReturn(new PageImpl<>(accommodationList));

        // when
        Page<Accommodation> accommodationPage = ar.findPageByAddressContains(address, pageRequest);

        // then
        assertThat(accommodationPage.getTotalElements()).isEqualTo(size);
        assertThat(accommodationPage.getNumber()).isEqualTo(pageNumber);
        assertThat(accommodationPage.getSize()).isEqualTo(size);
    }

    private List<Accommodation> createAccommodation(int size)  {
        List<Accommodation> accommodations = new ArrayList<>();

        for(int i = 1; i <= size; i++) {
            Map<String, List<String>> option = new HashMap<>();

            for(int j = 1; j <= 3; j++)  {
                option.put("category" + i, Arrays.asList("option1", "option2", "option3"));
            }

//            Option optionInstnace = Option.getInstnace();
//            optionInstnace.setOptionDetails(option);

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