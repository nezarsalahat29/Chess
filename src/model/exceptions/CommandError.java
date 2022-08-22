package model.exceptions;

public class CommandError extends Exception {
    String message;
    public CommandError(String s){
        super();
        message=s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
