package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.accommodation.web.dto.AccommodationListRes;
import clone.airbnbmongo.accommodation.web.dto.AccommodationRes;
import clone.airbnbmongo.common.exception.AccommodationNotFoundException;
import clone.airbnbmongo.common.queue.dto.AccommodationQueueRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<?> loadAccommodations(String address, Pageable pageable) {
        Page<Accommodation> accommodationPage = accommodationRepository.findByAddressContaining(address, pageable);

        return ResponseEntity.ok(AccommodationListRes.of(accommodationPage));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> loadAccommodation(long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(AccommodationNotFoundException::new);

        AccommodationRes accommodationRes = AccommodationRes.of(accommodation);
        return new ResponseEntity<>(accommodationRes, HttpStatus.OK);
    }

    public void createAccommodation(AccommodationQueueRes accommodationRes) {
        Accommodation accommodation = accommodationRes.toEntity();
        accommodationRepository.save(accommodation);
    }
}
