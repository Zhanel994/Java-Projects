package application.backend.com.services;

import application.backend.com.entities.Authority;
import application.backend.com.exceptions.NotFoundException;
import application.backend.com.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository repository;

    public Authority findByAuthority(String authority) {
        return repository
                .findByAuthority(authority)
                .orElseThrow(() -> new NotFoundException("Authority " + authority + " not exists!"));
    }
}
