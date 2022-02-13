package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.accommodation.web.dto.AccommodationRes;
import clone.airbnbmongo.common.exception.AccommodationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public ResponseEntity<?> loadAccommodations(String address, Pageable pageable) {
        return ResponseEntity.ok(accommodationRepository.findByAddressContaining(address, pageable));
    }

    public ResponseEntity<?> loadAccommodation(long accommodationId) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(AccommodationNotFoundException::new);

        AccommodationRes accommodationRes = AccommodationRes.of(accommodation);
        return new ResponseEntity<>(accommodationRes, HttpStatus.CREATED);
    }

    @Transactional
    public void createAccommodation(AccommodationRes accommodationRes) {
        Accommodation accommodation = accommodationRes.toEntity();
        accommodationRepository.save(accommodation);
    }
}
