package application.backend.com.repositories;

import application.backend.com.entities.Application;
import application.backend.com.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUser(User user);
}
