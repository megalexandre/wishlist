package infrastucture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wishlist.domain.exception.MaximumProductLimitExceeded;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MaximumProductLimitExceeded.class)
    public ResponseEntity<String> handleMaximumProductLimitExceeded(MaximumProductLimitExceeded ex) {
        return new ResponseEntity<>(ex.getMessage(), BAD_REQUEST);
    }

}
