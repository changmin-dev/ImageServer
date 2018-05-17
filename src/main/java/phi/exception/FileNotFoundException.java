package phi.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Created by changmin on 2018. 5. 17..
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
