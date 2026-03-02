package application.exam_7_zhanel_toishybekova.com.configurations.filters;

import application.exam_7_zhanel_toishybekova.com.services.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter
{
    private final TokenService service;

    @Override
    protected void doFilterInternal
    (
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain
    ) throws IOException
    {
        try
        {
            service.verify(request);
            chain.doFilter(request, response);
        }
        catch (Exception exception)
        {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            new ObjectMapper().writeValue(response.getOutputStream(), Map.of("error", "Invalid token"));
        }
    }
}
