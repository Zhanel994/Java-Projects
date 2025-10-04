package application.backend.com.services;

import application.backend.com.converter.TripConverter;
import application.backend.com.entities.Media;
import application.backend.com.entities.Trip;
import application.backend.com.entities.User;
import application.backend.com.filters.TripFilterParameters;
import application.backend.com.models.request.TripRequest;
import application.backend.com.models.response.TripDetailsResponse;
import application.backend.com.models.response.TripResponse;
import application.backend.com.models.response.TripSearchResponse;
import application.backend.com.repositories.TripRepository;
import application.backend.com.repositories.specification.TripSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository repository;
    private final MediaService mediaService;
    private final TripSpecification specification;
    private final TripConverter converter;

    public Trip createTrip(TripRequest request, MultipartFile file, User creator) throws IOException {
        Media media = mediaService.create(file);

        Trip trip = converter.toEntity(request, media, creator);
        return repository.save(trip);
    }

    public List<TripResponse> getAll() {
        List<Trip> trips = repository.findAll();
        return trips
                    .stream()
                    .map(trip -> converter.toResponse(trip, trip.getMedia()))
                    .toList();
    }

    public TripDetailsResponse getById(Long id) {
        Trip trip = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found!"));
        return converter.toDetailResponse(trip);
    }

    public TripResponse getTripResponse(Trip trip) {
        return converter.toResponse(trip, trip.getMedia());
    }


    public Page<TripSearchResponse> searchTrips(TripFilterParameters filter) {
        Page<Trip> tripsPage = repository.findAll(specification.get(filter), filter.getPageRequest());

        return tripsPage.map(trip -> new TripSearchResponse(
                trip.getId(),
                trip.getMedia().getUrl(),
                trip.getCity(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getFreePlaces()
        ));

    }

}
