package clone.airbnbmongo.accommodation.web;

import clone.airbnbmongo.accommodation.AccommodationType;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AccommodationRes implements Serializable {

    private Long id;

    private String name;

    private String description;

//    private Address address;

    private int personCount;

    private List<String> imagePath;

    private long basicPrice;

    private AccommodationType type;
}
