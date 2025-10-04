package application.backend.com.controllers;

import application.backend.com.controllers.interfaces.TripControllerAPI;
import application.backend.com.entities.Trip;
import application.backend.com.entities.User;
import application.backend.com.filters.TripFilterParameters;
import application.backend.com.models.request.TripRequest;
import application.backend.com.models.response.TripDetailsResponse;
import application.backend.com.models.response.TripResponse;
import application.backend.com.models.response.TripSearchResponse;
import application.backend.com.services.TripService;
import application.backend.com.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripController implements TripControllerAPI {
    private final TripService tripService;
    private final UserService userService;

    @Override
    public ResponseEntity<TripResponse> createTrip(TripRequest request, MultipartFile file, @AuthenticationPrincipal User user) throws IOException {
        Trip trip = tripService.createTrip(request, file, user);
        return ResponseEntity.status(201).body(tripService.getTripResponse(trip));
    }

    @Override
    public ResponseEntity<List<TripResponse>> getAll() {
        List<TripResponse> trips = tripService.getAll();
        return ResponseEntity.ok(trips);
    };

    @Override
    public ResponseEntity<TripDetailsResponse> getById(@PathVariable Long id){
        TripDetailsResponse tripDetailsResponse = tripService.getById(id);
        return ResponseEntity.ok(tripDetailsResponse);
    }

    @Override
    public ResponseEntity<List<TripSearchResponse>> search(@RequestBody TripFilterParameters filter) {
        List<TripSearchResponse> trips = tripService.searchTrips(filter).toList();
        return ResponseEntity.ok(trips);
    }


}
