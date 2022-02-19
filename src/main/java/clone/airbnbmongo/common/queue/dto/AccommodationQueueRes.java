package clone.airbnbmongo.common.queue.dto;

import clone.airbnbmongo.accommodation.AccommodationType;
import clone.airbnbmongo.accommodation.domain.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class AccommodationQueueRes {

    private Long id;

    private String name;

    private String description;

    private String address;

    private int personCount;

    private List<String> imagePath;

    private long basicPrice;

    private AccommodationType type;

    // TODO 위치 정보 필드 추가
    private double rating;

    private int reviewCount;

    private OptionQueueRes option;

    private String latitude;

    private String longitude;

    public Accommodation toEntity() {
        return Accommodation.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .personCount(this.personCount)
                .imagePath(this.imagePath)
                .basicPrice(this.basicPrice)
                .type(this.type)
                .address(address)
                .rating(this.rating)
                .reviewCount(this.reviewCount)
                .option(this.option.toEntity())
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}
