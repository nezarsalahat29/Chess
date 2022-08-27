package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.exceptions.*;
public class Game {
    String WhitePlayer;
    String BlackPlayer;
    private Board board;
    private boolean turn;
    private boolean gameInAction;
    private int numOfMoves=0;
    private int checkWKing=0;
    private int checkBKing=0;



    Scanner sc = new Scanner(System.in);

    public Game() {
        super();
        this.board=new Board();

        gameInAction = true;
        turn = false;


    }

    public void play() throws MoveError {
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
            // || ((board.getBCaptured()== board.getWCaptured()) && board.getBCaptured()>13)
            if (numOfMoves==50){
                System.out.println("\n!!! Draw !!!\n");
                gameInAction=false;
            }
            if (!turn)
            {
                if (board.isKingInCheck(turn)) {
                    System.out.println("\n\n Your King is in Danger , Move it!!\n");
                    checkWKing++;
                }
                System.out.print("\nEnter next move (white player): ");
            }
            else
            {
                if (board.isKingInCheck(turn)){
                    System.out.println("\n\nYour King is in Danger , Move it!!\n");
                    checkBKing++;
                }

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
        if (movementMatchFound && moveString.length() == 10)
        {
            handleMove(moveString);
        }
        else if(moveString.equals("exit")){
            System.out.println("Good bye.");
            this.gameInAction=false;
        }
        else
        {
            System.out.println("Try Again!");
        }

    }

    public void handleMove(String moveString) {

        Location from = board.getLocation(moveString.substring(5, 7));
        Location to = board.getLocation(moveString.substring(8, 10));

        Piece pieceToMove = from.getPiece();

        try {
            if (!thereIsPieceInLoc(from)) {
                throw new MoveError(MoveError.No_Piece);
            }
            if (!checkIfPieceToMoveMatchesPlayer(from)) {
                throw new MoveError(MoveError.NOT_YOUR_PIECE);
            }
            if(board.isKingInCheck(turn)){
                if (checkBKing==2){changeTurn(); throw new MoveError(MoveError.ChangeTurn);}
                if (checkWKing==2){changeTurn();throw new MoveError(MoveError.ChangeTurn);}
                throw new MoveError(MoveError.King_Is_Check);

            }

            pieceToMove.moveToLocation(to);
            numOfMoves++;
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
