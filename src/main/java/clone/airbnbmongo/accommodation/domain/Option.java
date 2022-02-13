package clone.airbnbmongo.accommodation.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Option {

    @Id
    private Long id;

    private String description;

}
