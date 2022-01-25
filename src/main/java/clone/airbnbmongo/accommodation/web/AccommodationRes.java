package clone.airbnbmongo.accommodation.web;

import clone.airbnbmongo.accommodation.AccommodationType;
import com.rabbitmq.client.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AccommodationRes {

    private Long id;

    private String name;

    private String description;

    private Address address;

    private int personCount;

    private List<String> imagePath;

    private long basicPrice;

    private AccommodationType type;
}
