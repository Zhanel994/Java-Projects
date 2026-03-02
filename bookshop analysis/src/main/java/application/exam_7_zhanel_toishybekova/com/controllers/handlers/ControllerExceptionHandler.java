package application.exam_7_zhanel_toishybekova.com.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception exception)
    {
        return ResponseEntity
                            .badRequest()
                            .body(Map.of("error", exception.getMessage()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<?> handleAuthorizationExceptions(AuthorizationDeniedException exception)
    {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of("error", exception.getMessage()));
    }
}
