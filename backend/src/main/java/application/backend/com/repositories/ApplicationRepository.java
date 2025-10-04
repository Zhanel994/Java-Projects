package application.backend.com.repositories;

import application.backend.com.entities.Application;
import application.backend.com.entities.Trip;
import application.backend.com.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByTrip(Trip trip);

    List<Application> findByUser(User user);

    Optional<Application> findByUserAndTrip(User user, Trip trip);
}
