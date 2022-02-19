package clone.airbnbmongo.common.queue.dto;

import clone.airbnbmongo.accommodation.domain.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class OptionQueueRes {

    private Map<String, List<String>> optionDetails;

    public Option toEntity() {
        return new Option(optionDetails);
    }
}
