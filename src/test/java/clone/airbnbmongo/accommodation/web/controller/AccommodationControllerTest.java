package clone.airbnbmongo.accommodation.web.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class AccommodationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("숙소 상세 정상 반환")
    void loadAccommodation() throws Exception {
        /**
         * {"id":1,"name":"testName1","description":"testDescription1","address":null,"personCount":2,"imagePath":["testImage1","testImage2","testImage3"],"basicPrice":10000,"type":"CONDO","rating":1.0,"reviewCount":10,"option":{"optionDetails":{"category1":["option1","option2","option3"]}},"latitude":"testLatitude1","longitude":"testLongitude1"}
         */


        mockMvc.perform(get("/accommodations/{accommodationId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("name", is("testName1")))
                .andExpect(jsonPath("description", is("testDescription1")));
    }

    @Test
    @DisplayName("숙소 리스트 정상 반환")
    void loadAccommodations() throws Exception {
        mockMvc.perform(get("/accommodations")
                        .queryParam("address", "test")
                        .queryParam("page", "0")
                        .queryParam("size", "5"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}