package clone.airbnbmongo.accommodation.domain.repository;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.common.BaseTest;
import clone.airbnbmongo.common.FixProperty;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.generator.BuilderArbitraryGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class AccommodationRepositoryTest extends BaseTest {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Test
    void findByAddressContaining() {
        String address = "Seoul";

        LongStream.rangeClosed(1, 100)
                .forEach(i -> {
                    Accommodation actual = createInstanceWithFixProperties(Accommodation.class, new FixProperty("id", i), new FixProperty("address", address + i));
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