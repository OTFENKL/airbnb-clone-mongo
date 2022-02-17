package clone.airbnbmongo.accommodation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class Option {

    private Map<String, List<String>> optionDetails;

    public Option(Map<String, List<String>> optionDetails) {
        this.optionDetails = optionDetails;
    }
}
