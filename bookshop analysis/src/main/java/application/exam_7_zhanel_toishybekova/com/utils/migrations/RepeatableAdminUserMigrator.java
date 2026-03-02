package application.exam_7_zhanel_toishybekova.com.utils.migrations;

import application.exam_7_zhanel_toishybekova.com.entities.User;
import application.exam_7_zhanel_toishybekova.com.models.Authority;
import application.exam_7_zhanel_toishybekova.com.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RepeatableAdminUserMigrator
{
    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    @Value("${application.admin.password}")
    private String password;

    @Bean(name = "admin-user-migrator")
    public CommandLineRunner migrate()
    {
        return args ->
        {
            userRepository.deleteByUsername("admin");

            User user = new User
            (
                    UUID.randomUUID().toString(),
                    "admin",
                    encoder.encode(password),
                    "admin@gmail.com",
                    List.of(new Authority("ADMIN"))
            );

            userRepository.save(user);
        };
    }
}
