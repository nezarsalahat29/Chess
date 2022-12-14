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

    public void play() throws MoveError, CommandError {
        this.board.init();
        System.out.println("\n\t\t\t\t Welcome to Chess\t\n");
        System.out.println("\n!!!!Enter 'exit' If you want to exit the game!!!!\n");
        System.out.print("Enter the white player name: ");
        WhitePlayer= sc.nextLine();
        System.out.print("Enter the black player name: ");
        BlackPlayer= sc.nextLine();

        //Game start here::
        while (gameInAction)
        {

            if(board.isKingCaptured)
            {
                break;
            }
            //draw after 120 move without king killed
            if (numOfMoves==120){
                System.out.println("\n!!! Draw !!!\n");
                gameInAction=false;
            }

            //white start with turn 1
            if (!turn)
            {
                //for switch turn after 3 tries of make a king free
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

    public void handleInput(String moveString) throws CommandError {

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
            throw new CommandError(CommandError.Wrong_Input);
        }

    }

    public void handleMove(String moveString) throws CommandError {
        // move f2 f3
        Location from = board.getLocation(moveString.substring(5, 7));
        Location to = board.getLocation(moveString.substring(8, 10));

        Piece pieceToMove = from.getPiece();
        /*if(pieceToMove.getName().equals("P")){
            board.promotion(from);
        }*/
        try {
            if (!thereIsPieceInLoc(from)) {
                throw new MoveError(MoveError.No_Piece);
            }
            if (!checkIfPieceToMoveMatchesPlayer(from)) {
                throw new MoveError(MoveError.NOT_YOUR_PIECE);
            }
            if(board.isKingInCheck(turn)){
                if (checkBKing==2){changeTurn();checkBKing=0; throw new MoveError(MoveError.ChangeTurn);}
                if (checkWKing==2){changeTurn();checkWKing=0;throw new MoveError(MoveError.ChangeTurn);}
                throw new MoveError(MoveError.King_Is_Check);

            }

            pieceToMove.moveToLocation(to);
            numOfMoves++;
            changeTurn();
        } catch (MoveError | CommandError e) {
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
