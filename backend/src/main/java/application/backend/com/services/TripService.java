package application.backend.com.services;

import application.backend.com.repositories.TripRepository;
import application.backend.com.repositories.specification.TripSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository repository;
    private final TripSpecification specification;
    
}
