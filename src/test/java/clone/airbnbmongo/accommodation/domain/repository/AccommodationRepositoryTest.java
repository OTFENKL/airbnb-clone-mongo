package clone.airbnbmongo.accommodation.domain.repository;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.common.FixProperty;
import clone.airbnbmongo.common.accommodation.BaseAccommodationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;

import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Commit
public class AccommodationRepositoryTest extends BaseAccommodationTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    @Disabled
    void findByAddressContaining() {
        String address = "Seoul";

        LongStream.rangeClosed(1, 100)
                .forEach(i -> {
                    Accommodation actual = createInstanceWithFixProperties(Accommodation.class, FixProperty.of("id", i), FixProperty.of("address", address + i));
                    accommodationRepository.save(actual);
                });

        PageRequest page = PageRequest.of(0, 10);
        Page<Accommodation> findedAccommodations = accommodationRepository.findByAddressContaining(address, page);

        assertThat(findedAccommodations.getTotalPages()).isEqualTo(10);
        assertThat(findedAccommodations.getTotalElements()).isEqualTo(100L);
        assertThat(findedAccommodations.getContent().size()).isEqualTo(10);
        assertThat(findedAccommodations.getContent().get(0).getAddress()).contains(address);
    }
}
