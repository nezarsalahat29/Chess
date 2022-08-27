package model.exceptions;

public class CommandError extends Exception {
    public static String Wrong_Promotion="\nWrong Promotion\n";
    public static String Wrong_Input="\nTry Again!! Wrong Input\n";
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
