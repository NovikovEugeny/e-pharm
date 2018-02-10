package by.pharmsystem.userservice.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(){
        super();
    }

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(Exception e){
        super(e);
    }

    public BadRequestException(String message, Exception e){
        super(message, e);
    }

}
