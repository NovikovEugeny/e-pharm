package by.pharmsystem.userservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchEmail extends RuntimeException {

    public NoSuchEmail() {
        super();
    }

    public NoSuchEmail(String message) {
        super(message);
    }

    public NoSuchEmail(Exception e) {
        super(e);
    }

    public NoSuchEmail(String message, Exception e) {
        super(message, e);
    }
}
