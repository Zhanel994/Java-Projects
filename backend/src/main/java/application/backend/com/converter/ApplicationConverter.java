package application.backend.com.converter;

import application.backend.com.entities.Application;
import application.backend.com.entities.Trip;
import application.backend.com.entities.User;
import application.backend.com.entities.enums.ApplicationStatus;
import application.backend.com.models.request.ApplicationRequest;
import application.backend.com.models.response.ApplicationResponse;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConverter {
    public Application toEntity(ApplicationRequest request, Trip trip, User user) {
        Application application = new Application();
        application.setTrip(trip);
        application.setUser(user);
        application.setStatus(ApplicationStatus.PENDING);
        return application;
    }

    public ApplicationResponse toResponse(Application application) {
        return new ApplicationResponse(
          application.getTrip().getId(),
          application.getTrip().getTitle(),
          application.getUser().getUsername(),
          application.getStatus()
        );
    }
}
