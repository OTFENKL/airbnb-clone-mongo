package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class AccommodationWriter {

    private final AccommodationRepository accommodationRepository;

    public Accommodation save(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }
}
