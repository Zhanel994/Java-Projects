package application.backend.com.repositories;

import application.backend.com.entities.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends CrudRepository<Media, Long> {
    Optional<Media> findByName(String name);
}