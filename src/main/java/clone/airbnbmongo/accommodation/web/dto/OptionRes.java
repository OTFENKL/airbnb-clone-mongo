package clone.airbnbmongo.accommodation.web.dto;

import clone.airbnbmongo.accommodation.domain.Option;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter @Setter
public class OptionRes {

    private Map<String, List<String>> optionDetails;

    public static OptionRes of(Option option) {
        return new OptionRes(option.getOptionDetails());
    }

    public OptionRes(Map<String, List<String>> optionDetails) {
        this.optionDetails = optionDetails;
    }
}
