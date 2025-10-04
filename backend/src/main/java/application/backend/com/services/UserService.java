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

    public UserResponse create(RegistrationRequest request, MultipartFile avatarFile) throws IOException {
        if (repository.existsByUsernameOrEmail(request.username(), request.email())) {
            throw new IllegalArgumentException("Username or email is already in use!");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));
        user.setName(request.name());
        user.setBio(request.bio());
        user.setPhone(request.phone());
        user.setGender(request.gender());
        user.setCreatedAt(LocalDateTime.now());

        if (avatarFile != null && !avatarFile.isEmpty()) {
            Media avatar = mediaService.create(avatarFile);
            user.setAvatar(avatar);
        }

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
        String avatarUrl = (user.getAvatar() != null) ? user.getAvatar().getUrl() : null;

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                avatarUrl,
                user.getName(),
                user.getBio(),
                user.getPhone(),
                user.getGender(),
                user.getPosts() != null ? user.getPosts().size() : 0,
                user.getSubscriptionCount(),
                user.getSubscribersCount(),
                user.getCreatedAt()
        );
    }

    private UserSearchResponse toSearchResponse(User user) {
        List<PostResponse> posts = new ArrayList<>();

        if (user.getPosts() != null) {
            for (Post post : user.getPosts()) {
                posts.add(new PostResponse(
                        post.getId(),
                        post.getMedia().getUrl(),
                        post.getCaption(),
                        post.getLikes() != null ? post.getLikes().size() : 0,
                        post.getComments() != null ? post.getComments().size() : 0,
                        post.getCreatedAt(),
                        user.getUsername()
                ));
            }
        }

        String avatarUrl = (user.getAvatar() != null) ? user.getAvatar().getUrl() : null;

        return new UserSearchResponse(
                user.getUsername(),
                avatarUrl,
                user.getName(),
                user.getBio(),
                posts
        );
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found!"));
    }

    public List<UserSearchResponse> search(TripFilterParameters filter) {
        return repository.findAll(specification.get(filter), filter.getPageRequest())
                .stream()
                .map(this::toSearchResponse)
                .toList();
    }
}
