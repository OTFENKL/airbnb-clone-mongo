package clone.airbnbmongo.common.queue.dto;

import clone.airbnbmongo.accommodation.domain.Option;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data @NoArgsConstructor
public class OptionQueueRes {

    private Map<String, List<String>> optionDetails;

    @Builder
    public OptionQueueRes(Map<String, List<String>> optionDetails) {
        this.optionDetails = optionDetails;
    }

    public Option toEntity() {
        return new Option(optionDetails);
    }
}
