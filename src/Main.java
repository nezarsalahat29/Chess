import model.Game;
import model.exceptions.CommandError;
import model.exceptions.MoveError;

public class Main {
    public static void main(String[] args) throws MoveError, CommandError {
        new Game().play();
    }
}
