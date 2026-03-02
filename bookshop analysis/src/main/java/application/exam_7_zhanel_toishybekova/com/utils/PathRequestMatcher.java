package application.exam_7_zhanel_toishybekova.com.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;

import java.util.List;

public class PathRequestMatcher
{
    private static final List<String> patterns = List.of
    (
            "/api/v1/users/login",
            "/api/v1/users/registration",
            "/api/v1/tokens/refresh",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger/**"
    );
    private static final AntPathMatcher matcher = new AntPathMatcher();

    public static boolean matches(HttpServletRequest request)
    {
        String path = request.getRequestURI();
        return patterns.stream().anyMatch(it -> matcher.match(it, path));
    }
}
