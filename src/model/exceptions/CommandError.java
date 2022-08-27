package model.exceptions;

public class CommandError extends Exception {
    public static String Wrong_Promotion="\nWrong Promotion\n";
    String message;
    public CommandError(String s){
        super();
        message=s+ " Wrong Command, Try Again!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
