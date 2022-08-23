package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.exceptions.*;
//TODO:: ADD Exit Command and Move Word in Game
public class Game {
    String WhitePlayer;
    String BlackPlayer;
    private Board board;
    private boolean turn;
    private boolean gameInAction;
    private ArrayList<String> validMoves;
    Scanner sc = new Scanner(System.in);

    public Game() {
        super();
        this.board=new Board();

        gameInAction = true;
        turn = false;
        validMoves = new ArrayList<>();

    }

    public void play()
    {
        this.board.init();
        System.out.print("Enter the white player name: ");
        WhitePlayer= sc.nextLine();
        System.out.print("Enter the black player name: ");
        BlackPlayer= sc.nextLine();

        while (gameInAction)
        {

            if(board.isKingCaptured)
            {
                break;
            }

            if (!turn)
            {
                System.out.print("\nEnter next move (white player): ");
            }
            else
            {
                System.out.print("\nEnter next move (black player): ");
            }

            String play = sc.nextLine();
            handleInput(play);
        }
    }

    private void changeTurn() {
        turn = !turn;
    }

    public void handleInput(String moveString)
    {

        Pattern patternMovement = Pattern.compile("[a-h][1-8] [a-h][1-8]", Pattern.CASE_INSENSITIVE);
        Matcher movementMatcher = patternMovement.matcher(moveString);
        boolean movementMatchFound = movementMatcher.find();
        if (movementMatchFound && moveString.length() == 5)
        {
            handleMove(moveString);
        }
        else
        {
            System.out.println("\n!!! This is not a valid input!!!\n");
        }

    }

    public void handleMove(String moveString) {

        Location from = board.getLocation(moveString.substring(0, 2));
        Location to = board.getLocation(moveString.substring(3, 5));

        Piece pieceToMove = from.getPiece();

        try {
            if (!thereIsPieceInLoc(from)) {
                throw new MoveError("No Piece in Location");
            }
            if (!checkIfPieceToMoveMatchesPlayer(from)) {
                throw new MoveError("Not your Piece");
            }

            pieceToMove.moveToLocation(to);
            validMoves.add(moveString);
            changeTurn();
        } catch (MoveError e) {
            System.out.println(e.getMessage());
        }

    }


    private boolean thereIsPieceInLoc(Location loc)
    {
        return board.getPieceAt(loc) != null;
    }

    private boolean checkIfPieceToMoveMatchesPlayer(Location locTo) {
        return (!turn && board.getPieceAt(locTo).color.equals(Color.white))
                || (turn && board.getPieceAt(locTo).color.equals(Color.black));
    }


}
