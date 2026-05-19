package in.digeshwar.Exception;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyErrorResponse {

    private final String message;
    private final int status;
    private final String path;
    private final String error;
    private final LocalDateTime timestamp;

    public MyErrorResponse(LocalDateTime timestamp, int status,  String error, String message, String requestURI) {
        this.message = message;
        this.status = status;
        this.path = requestURI;
        this.error = error;
        this.timestamp = timestamp;
    }
}