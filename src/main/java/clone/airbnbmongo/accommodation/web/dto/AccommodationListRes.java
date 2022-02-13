package clone.airbnbmongo.accommodation.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class AccommodationListRes {

    private List<AccommodationRes> accommodationResList;

    private int pageNumber;

    private int pageSize;

    private int totalCount;

}
