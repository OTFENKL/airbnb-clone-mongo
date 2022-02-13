package clone.airbnbmongo.accommodation.domain.repository;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccommodationRepository extends MongoRepository<Accommodation, Long> {

    Page<Accommodation> findByAddressContaining(String address, Pageable pageable);
}
