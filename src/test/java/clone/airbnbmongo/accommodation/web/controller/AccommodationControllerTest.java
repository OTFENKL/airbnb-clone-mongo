package clone.airbnbmongo.accommodation.web.controller;

import clone.airbnbmongo.common.accommodation.BaseAccommodationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class AccommodationControllerTest extends BaseAccommodationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("숙소 상세 정상 반환")
    void loadAccommodation() throws Exception {
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