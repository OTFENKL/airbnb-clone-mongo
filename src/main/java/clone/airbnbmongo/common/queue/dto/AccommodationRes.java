package clone.airbnbmongo.common.queue.dto;

import clone.airbnbmongo.accommodation.AccommodationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
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

    // TODO 위치 정보 필드 추가
    private double rating;

    private int reviewCount;

    private List<OptionRes> options = new ArrayList<>();

    private String latitude;

    private String longitude;
}
