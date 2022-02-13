package clone.airbnbmongo.accommodation.web.controller;

import clone.airbnbmongo.accommodation.api.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/accommodations")
    public ResponseEntity<?> loadAccommodations(@RequestParam("address") String address, Pageable pageable) {
        return accommodationService.loadAccommodations(address, pageable);
    }

    @GetMapping("/accommodations/{accommodationId}")
    public ResponseEntity<?> loadAccommodation(@PathVariable long accommodationId) {
        return accommodationService.loadAccommodation(accommodationId);
    }
}
