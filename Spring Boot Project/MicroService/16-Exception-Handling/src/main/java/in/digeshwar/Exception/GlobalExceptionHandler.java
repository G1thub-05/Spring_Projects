package in.digeshwar.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MyErrorResponse> handleUserNotFound(
            UserNotFoundException ex,
            HttpServletRequest request) {

        MyErrorResponse error = new MyErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(), // Set status code in JSON response
                HttpStatus.NOT_FOUND.name(),  // Set status name in JSON response
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}