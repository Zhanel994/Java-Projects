package application.exam_7_zhanel_toishybekova.com.services;

import application.exam_7_zhanel_toishybekova.com.entities.User;
import application.exam_7_zhanel_toishybekova.com.models.Authority;
import application.exam_7_zhanel_toishybekova.com.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService
{
    private final PasswordEncoder encoder;
    private final UserRepository repository;

    public void create(User user)
    {
        if (existsByUsername(user.getUsername()))
        {
            throw new IllegalArgumentException("Username is already in use!");
        }

        String password = user.getPassword();
        user.setPassword(encoder.encode(password));

        Authority authority = new Authority("USER");
        user.addAuthority(authority);

        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return repository
                        .findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    private boolean existsByUsername(String username)
    {
        return repository.existsByUsername(username);
    }
}
