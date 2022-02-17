package clone.airbnbmongo.common.queue.dto;

import clone.airbnbmongo.accommodation.domain.Option;

import java.util.List;
import java.util.Map;

public class OptionQueueRes {

    private Map<String, List<String>> optionDetails;

    public Option toEntity() {
        return new Option(optionDetails);
    }
}
