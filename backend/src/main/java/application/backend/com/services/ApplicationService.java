package application.backend.com.services;

import application.backend.com.converter.ApplicationConverter;
import application.backend.com.entities.Application;
import application.backend.com.entities.Trip;
import application.backend.com.entities.User;
import application.backend.com.entities.enums.ApplicationStatus;
import application.backend.com.models.request.ApplicationRequest;
import application.backend.com.models.response.ApplicationResponse;
import application.backend.com.repositories.ApplicationRepository;
import application.backend.com.repositories.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final TripRepository tripRepository;
    private final ApplicationConverter converter;

    public ApplicationResponse createApplication(ApplicationRequest request, User user) {
        Trip trip = tripRepository.findById(request.tripId()).orElseThrow(() -> new IllegalArgumentException("Trip with id: " + request.tripId() + " not found!"));

        Application application = new Application();
        application.setTrip(trip);
        application.setUser(user);
        application.setStatus(ApplicationStatus.PENDING);

        Application saved = applicationRepository.save(application);
        return converter.toResponse(saved);
    }

    public List<ApplicationResponse> getApplicationsByUser(User user) {
        List<Application> applications = applicationRepository.findByUser(user);
        return applications
                            .stream()
                            .map(converter::toResponse)
                            .toList();
    }

    public List<ApplicationResponse> getApplicationByTrip(Trip trip) {
        List<Application> applications = applicationRepository.findByTrip(trip);
        return applications
                .stream()
                .map(converter::toResponse)
                .toList();
    }

    public ApplicationResponse updateStatus(Long id, ApplicationStatus status, User creator) {
      Application application = applicationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Application not found!"));

      Trip trip = application.getTrip();

      if(!trip.getCreator().getId().equals(creator.getId())) {
          throw new IllegalArgumentException("You are not creator of the trip!");
      }

      application.setStatus(status);
      Application updated = applicationRepository.save(application);

      if(status == ApplicationStatus.ACCEPTED) {
          trip.getParticipants().add(application.getUser());
          tripRepository.save(trip);
      }
      return converter.toResponse(updated);
    }
}
