package phi.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by changmin on 2018. 5. 18..
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestPathException extends RequestException{

    public InvalidRequestPathException(String message) {
        super(message);
    }

    public InvalidRequestPathException(String message, Throwable cause) {
        super(message, cause);
    }
}
