import model.Board;
import model.Game;
public class Main {
    public static void main(String[] args) {
        Board board= new Board();
        board.init();
        new Game(board).play();
    }
}

//TODO:: ADD Exit Command and Move Word in Game
//TODO:: Complete The Pieces with Strategy
//TODO:: State and Strategy(Valid) and Template Design Patterns