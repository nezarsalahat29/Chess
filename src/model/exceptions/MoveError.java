package model.exceptions;

public class MoveError extends Exception {
    String message;
    public MoveError(String s){
        super();
        message=s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
