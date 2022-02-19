package clone.airbnbmongo.accommodation.web.dto;

import clone.airbnbmongo.accommodation.domain.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class AccommodationListRes {

    private List<AccommodationRes> accommodationResList;

    private int pageNumber;

    private int pageSize;

    private long totalCount;

    public static AccommodationListRes of(Page<Accommodation> accommodationPage) {
        List<AccommodationRes> accommodationResList = accommodationPage.getContent().stream()
                .map(AccommodationRes::of)
                .collect(Collectors.toList());

        return AccommodationListRes.builder()
                .accommodationResList(accommodationResList)
                .pageNumber(accommodationPage.getNumber())
                .pageSize(accommodationPage.getSize())
                .totalCount(accommodationPage.getTotalElements())
                .build();
    }
}
