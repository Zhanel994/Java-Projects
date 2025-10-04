package application.backend.com.converter;

import application.backend.com.entities.Media;
import application.backend.com.entities.Trip;
import application.backend.com.entities.User;
import application.backend.com.models.request.TripRequest;
import application.backend.com.models.response.TripDetailsResponse;
import application.backend.com.models.response.TripResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TripConverter {

    public Trip toEntity(TripRequest request, Media media, User creator) {
        Trip trip = new Trip();
        trip.setTitle(request.title());
        trip.setCity(request.city());
        trip.setDescription(request.description());
        trip.setStartDate(request.startDate());
        trip.setEndDate(request.endDate());
        trip.setMaxParticipants(request.MaxParticipants());
        trip.setCreator(creator);
        trip.setMedia(media);

        return trip;
    }

    public TripResponse toResponse(Trip trip, Media media) {
        return new TripResponse(
                trip.getId(),
                trip.getMedia().getUrl(),
                trip.getCity(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getFreePlaces()
        );
    }

    public TripDetailsResponse toDetailResponse(Trip trip) {
        List<String> participantsName = trip.getParticipants() != null ? trip.getParticipants()
                                                                                            .stream()
                                                                                            .map(User::getUsername)
                                                                                            .toList()
                                                                                            :List.of();
        return new TripDetailsResponse(
                trip.getId(),
                trip.getMedia().getUrl(),
                trip.getDescription(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getMaxParticipants(),
                trip.getFreePlaces(),
                participantsName
        );
    }
}
