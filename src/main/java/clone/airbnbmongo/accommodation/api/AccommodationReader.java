package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.domain.repository.AccommodationRepository;
import clone.airbnbmongo.common.exception.AccommodationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class AccommodationReader {

    private final AccommodationRepository accommodationRepository;

    public Accommodation findById(Long id) {
        return accommodationRepository.findById(id)
                .orElseThrow(AccommodationNotFoundException::new);
    }

    public Page<Accommodation> findPageByAddressContains(String address, Pageable pageable) {
        return accommodationRepository.findByAddressContaining(address, pageable);
    }
}
