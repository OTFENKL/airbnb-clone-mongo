package clone.airbnbmongo.accommodation.api;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import clone.airbnbmongo.accommodation.web.dto.AccommodationListRes;
import clone.airbnbmongo.accommodation.web.dto.AccommodationRes;
import clone.airbnbmongo.common.queue.dto.AccommodationQueueRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public record AccommodationService(AccommodationWriter aw, AccommodationReader ar) {

    public AccommodationListRes loadAccommodations(String address, Pageable pageable) {
        //전달받은 address를 파라미터로 DB에 페이지 단위로 조회한다.
        Page<Accommodation> accommodationPage = ar.findPageByAddressContains(address, pageable);

        //Body가 DTO인 ResponseEntity를 반환한다.
        return AccommodationListRes.of(accommodationPage);
    }

    public AccommodationRes loadAccommodation(long accommodationId) {
        //전달받은 id를 파라미터로 DB에 조회한다.
        Accommodation accommodation = ar.findById(accommodationId);

        //조회한 Entity를 DTO로 변환한다.
        AccommodationRes accommodationRes = AccommodationRes.of(accommodation);

        //Body가 DTO인 ResponseEntity를 반환한다.
        return accommodationRes;
    }

    public AccommodationRes createAccommodation(AccommodationQueueRes accommodationRes) {
        //전달받은 DTO를 Entity로 변환한다.
        Accommodation accommodation = accommodationRes.toEntity();

        //Entity를 DB에 저장한다.
        Accommodation savedAccommodation = aw.save(accommodation);

        //저장한 Entity를 DTO로 전환 후 반환한다.
        return AccommodationRes.of(savedAccommodation);
    }
}
