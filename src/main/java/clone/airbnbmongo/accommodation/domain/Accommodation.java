package clone.airbnbmongo.accommodation.domain;

import clone.airbnbmongo.accommodation.AccommodationType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Document(collection = "accommodation")
public class Accommodation {

    @Id
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

    private List<Option> options = new ArrayList<>();

    private String latitude;

    private String longitude;

    @Builder
    public Accommodation(Long id, String name, String description, String address, int personCount, List<String> imagePath, long basicPrice, AccommodationType type, double rating, int reviewCount, List<Option> options, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.personCount = personCount;
        this.imagePath = imagePath;
        this.basicPrice = basicPrice;
        this.type = type;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.options = options;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
