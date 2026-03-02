package application.backend.com.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.AntPathMatcher;

import java.util.List;

public class PathRequestMatcher {
    private static final List<String> patterns = List.of (
            "POST /api/v1/users/login",
            "POST /api/v1/users/registration",
            "POST /api/v1/tokens/refresh",

            "GET /api/v1/media/**",

            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger/**"
    );

    private static final AntPathMatcher matcher = new AntPathMatcher();

    public static boolean matches(HttpServletRequest request) {
        return patterns.stream().anyMatch(it -> matcher.match(it, getPath(request, it)));
    }

    private static String getPath(HttpServletRequest request, String pattern) {
        String path = request.getRequestURI();

        if (pattern.split(" ").length < 2) {
            return path;
        }
        else {
            String method = request.getMethod();
            return "%s %s".formatted(method, path);
        }
    }
}
