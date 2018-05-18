package phi.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by changmin on 2018. 5. 18..
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestException extends RuntimeException{
    public RequestException(String message) {
        super(message);
    }

    public RequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
