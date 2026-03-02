package application.exam_7_zhanel_toishybekova.com.configurations;

import application.exam_7_zhanel_toishybekova.com.configurations.filters.AuthorizationFilter;
import application.exam_7_zhanel_toishybekova.com.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration
{
    private final TokenService service;

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filters(HttpSecurity security) throws Exception
    {
        SessionCreationPolicy policy = SessionCreationPolicy.STATELESS;

        return security
                    .authorizeHttpRequests(it -> it.anyRequest().permitAll())
                    .httpBasic(Customizer.withDefaults())
                    .sessionManagement
                    (
                            management -> management.sessionCreationPolicy(policy)
                    )
                    .formLogin(AbstractHttpConfigurer::disable)
                    .logout(AbstractHttpConfigurer::disable)
                    .csrf(AbstractHttpConfigurer::disable)
                    .addFilterBefore(new AuthorizationFilter(service), UsernamePasswordAuthenticationFilter.class)
                    .build();
    }
}
