import model.Board;
import model.Game;
import model.exceptions.CommandError;
import model.exceptions.MoveError;

public class Main {
    public static void main(String[] args) throws MoveError, CommandError {
        new Game().play();
    }
}

//TODO::
//3) DESIGN PATTERN , CLEAN CODE , SOLID CODE (TOMORROW)
// 4)REPORT AND VIDEO (TOMORROW AND SATURDAY)