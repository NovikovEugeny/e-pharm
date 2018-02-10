package by.pharmsystem.gateway.security;

public class ExpiredTokenException extends Exception {

    public ExpiredTokenException(){
        super();
    }

    public ExpiredTokenException(String message){
        super(message);
    }

    public ExpiredTokenException(Exception e){
        super(e);
    }

    public ExpiredTokenException(String message, Exception e){
        super(message, e);
    }

}
