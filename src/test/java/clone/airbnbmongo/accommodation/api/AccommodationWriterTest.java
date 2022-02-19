package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.common.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccommodationWriterTest extends BaseTest {

    @Mock
    AccommodationRepository mockAccommodationRepository;

    private AccommodationWriter aw;

    @BeforeEach
    public void init()  {
        aw = new AccommodationWriter(mockAccommodationRepository);
    }

    @Test
    public void save()  {
        // given
        Accommodation accommodation = createInstance(Accommodation.class);
        when(mockAccommodationRepository.save(accommodation)).thenReturn(accommodation);

        // when
        Accommodation savedAccommodation = aw.save(accommodation);

        // then
        assertThat(savedAccommodation.getId()).isEqualTo(accommodation.getId());
        assertThat(savedAccommodation.getName()).isEqualTo(accommodation.getName());
        assertThat(savedAccommodation.getDescription()).isEqualTo(accommodation.getDescription());
        assertThat(savedAccommodation.getPersonCount()).isEqualTo(accommodation.getPersonCount());
        assertThat(savedAccommodation.getImagePath()).isEqualTo(accommodation.getImagePath());
        assertThat(savedAccommodation.getBasicPrice()).isEqualTo(accommodation.getBasicPrice());
        assertThat(savedAccommodation.getAddress()).isEqualTo(accommodation.getAddress());
        assertThat(savedAccommodation.getType()).isEqualTo(accommodation.getType());
        assertThat(savedAccommodation.getRating()).isEqualTo(accommodation.getRating());
        assertThat(savedAccommodation.getReviewCount()).isEqualTo(accommodation.getReviewCount());
        assertThat(savedAccommodation.getLatitude()).isEqualTo(accommodation.getLatitude());
        assertThat(savedAccommodation.getLongitude()).isEqualTo(accommodation.getLongitude());


    }

}