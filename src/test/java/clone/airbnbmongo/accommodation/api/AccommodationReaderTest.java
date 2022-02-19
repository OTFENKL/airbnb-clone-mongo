package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.common.accommodation.BaseAccommodationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.mockito.Mockito.when;

class AccommodationReaderTest extends BaseAccommodationTest {

    @Mock
    AccommodationRepository accommodationRepository;

    private AccommodationReader ar;

    @BeforeEach
    public void init()  {
        ar = new AccommodationReader(accommodationRepository);
    }

    @Test
    public void findById()  {
        long accommodationId = 1L;

//        when(ar.findById(accommodationId)).thenReturn(accommodation);
    }

    @Test
    public void findByIdFailedByNonExistId()    {
        long accommodationId = -1L;
        Optional<Accommodation> accommodationOptional = accommodationRepository.findById(accommodationId);

        when(ar.findById(accommodationId)).thenReturn(accommodationOptional.get());
    }

    @Test
    public void findPageByAddressContains() {
        String address = "testAddress";
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Accommodation> accommodationPage = accommodationRepository.findByAddressContaining(address, pageRequest);

        when(ar.findPageByAddressContains(address, pageRequest)).thenReturn(accommodationPage);
    }



}