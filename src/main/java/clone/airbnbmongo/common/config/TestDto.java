package clone.airbnbmongo.common.config;

import lombok.*;

import java.io.Serializable;

@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class TestDto {

    private String hello;
}
