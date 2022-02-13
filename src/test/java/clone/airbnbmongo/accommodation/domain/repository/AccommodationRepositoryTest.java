package clone.airbnbmongo.accommodation.domain.repository;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.common.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class AccommodationRepositoryTest extends BaseTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    void findByAddressContaining() {
        Accommodation instance = createInstance(Accommodation.class);
        accommodationRepository.save(instance);

        PageRequest page = PageRequest.of(0, 10);
        Page<Accommodation> allByAddressContainsJPQL = accommodationRepository.findByAddressContaining(instance.getAddress(), page);

        System.out.println("result : " + allByAddressContainsJPQL.getContent());
    }
}