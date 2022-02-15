package clone.airbnbmongo.accommodation.web.dto;

import clone.airbnbmongo.accommodation.AccommodationType;
import clone.airbnbmongo.accommodation.domain.Accommodation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class AccommodationRes {

    private Long id;

    private String name;

    private String description;

    private String address;

    private int personCount;

    private List<String> imagePath;

    private long basicPrice;

    private AccommodationType type;

    private double rating;

    private int reviewCount;

    private List<OptionRes> options = new ArrayList<>();

    private String latitude;

    private String longitude;

    public static AccommodationRes of(Accommodation accommodation) {
        return AccommodationRes.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .description(accommodation.getDescription())
                .personCount(accommodation.getPersonCount())
                .imagePath(accommodation.getImagePath())
                .basicPrice(accommodation.getBasicPrice())
                .type(accommodation.getType())
                .rating(accommodation.getRating())
                .reviewCount(accommodation.getReviewCount())
//                .options()
                .latitude(accommodation.getLatitude())
                .longitude(accommodation.getLongitude())
                .build();
    }

    public Accommodation toEntity() {
        return Accommodation.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .personCount(this.personCount)
                .imagePath(this.imagePath)
                .basicPrice(this.basicPrice)
                .type(this.type)
                .rating(this.rating)
                .reviewCount(this.reviewCount)
//                .options(this.options)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}
