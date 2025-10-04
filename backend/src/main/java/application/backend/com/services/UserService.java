package application.backend.com.services;

import application.backend.com.entities.Authority;
import application.backend.com.entities.Media;
import application.backend.com.entities.User;
import application.backend.com.filters.TripFilterParameters;
import application.backend.com.models.request.RegistrationRequest;
import application.backend.com.models.response.UserResponse;
import application.backend.com.repositories.UserRepository;
import application.backend.com.repositories.specification.TripSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final PasswordEncoder encoder;
    private final AuthorityService authorityService;
    private final UserRepository repository;
    private final TripSpecification specification;
    private final MediaService mediaService;

    public UserResponse create(RegistrationRequest request) {
        if (repository.existsByUsernameOrEmail(request.username(), request.email())) {
            throw new IllegalArgumentException("Username or email is already in use!");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));
        user.setName(request.name());
        user.setCreatedAt(LocalDateTime.now());

        Authority authority = authorityService.findByAuthority("USER");
        user.addAuthority(authority);

        User saved = repository.save(user);
        return toResponse(saved);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    private UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt()
        );
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found!"));
    }

}
