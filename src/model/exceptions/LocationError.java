package model.exceptions;

public class LocationError extends Exception{
    String message;
    public LocationError(String s){
        super();
        message=s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
