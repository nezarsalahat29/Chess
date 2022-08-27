package model;
import model.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board
{
    public  Location[][] locations;
    boolean isKingCaptured;
    boolean isBKingCheck,isWKingCheck;
    Rook Black_Rook1,Black_Rook2,White_Rook1,White_Rook2;
    Knight Black_Knight1,Black_Knight2,White_Knight1,White_Knight2;
    Bishop Black_Bishop1,Black_Bishop2,White_Bishop1,White_Bishop2;
    Queen Black_Queen,White_Queen;
    King Black_King,White_King;
    Piece []Black_Pawn;
    Piece []White_Pawn;
    ArrayList<Piece> wBoard;
    ArrayList<Piece> bBoard;
    public Board()
    {
        locations = new Location[8][8];
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                locations[i][j] = new Location(i, j);
            }
        }
        Black_Pawn=new Pawn[8];
        White_Pawn=new Pawn[8];
        isKingCaptured = false;
        bBoard=new ArrayList<>();
        wBoard=new ArrayList<>();
        isBKingCheck=false;
        isWKingCheck=false;
    }

    public void init()
    {
        // BLACK
        Black_Rook1=new Rook(Color.black, locations[0][0], this);
        Black_Rook2=new Rook(Color.black, locations[0][7], this);
        Black_Knight1=new Knight(Color.black, locations[0][1], this);
        Black_Knight2=new Knight(Color.black, locations[0][6], this);
        Black_Bishop1=new Bishop(Color.black, locations[0][2], this);
        Black_Bishop2=new Bishop(Color.black, locations[0][5], this);
        Black_Queen=new Queen(Color.black, locations[0][3], this);
        Black_King=new King(Color.black, locations[0][4], this);
        bBoard.add(Black_King);
        bBoard.add(Black_Bishop1);
        bBoard.add(Black_Bishop2);
        bBoard.add(Black_Knight1);
        bBoard.add(Black_Knight2);
        bBoard.add(Black_Rook1);
        bBoard.add(Black_Rook2);
        bBoard.add(Black_Queen);

        for (int i = 0; i < 8; i++)
        {
            Black_Pawn[i]=new Pawn(Color.black, locations[1][i], this);
            bBoard.add(Black_Pawn[i]);
        }


        // WHITE
        White_Rook1= new Rook(Color.white, locations[7][0], this);
        White_Rook2=new Rook(Color.white, locations[7][7], this);
        White_Knight1=new Knight(Color.white, locations[7][1], this);
        White_Knight2=new Knight(Color.white, locations[7][6], this);
        White_Bishop1=new Bishop(Color.white, locations[7][2], this);
        White_Bishop2=new Bishop(Color.white, locations[7][5], this);
        White_Queen=new Queen(Color.white, locations[7][3], this);
        White_King= new King(Color.white, locations[7][4], this);
        wBoard.add(White_King);
        wBoard.add(White_Bishop1);
        wBoard.add(White_Bishop2);
        wBoard.add(White_Knight1);
        wBoard.add(White_Knight2);
        wBoard.add(White_Rook1);
        wBoard.add(White_Rook2);
        wBoard.add(White_Queen);
        for (int i = 0; i < 8; i++)
        {
            White_Pawn[i]=new Pawn(Color.white, locations[6][i], this);
            wBoard.add(White_Pawn[i]);
        }

    }
    public Location getLocation(String str)
    {
        Location currentLoc = new Location(str);
        return locations[currentLoc.getRow()][currentLoc.getCol()];
    }

    public Piece getPieceAt(Location loc)
    {
        return locations[loc.getRow()][loc.getCol()].getPiece();
    }

    public void movePiece(Location from, Location to) throws MoveError, CommandError {
        //check if Pawn can promote
        if(getPieceAt(from).getClass().isInstance(Pawn.class)){
           promotion(from);
        }
        // 2 types of move
        if (getPieceAt(to) == null) {
            movePieceWithoutCapturing(from, to);
        } else if (getPieceAt(from).color != getPieceAt(to).color) {
            movePieceCapturing(from, to);
        } else {
            throw new MoveError(MoveError.Not_Free);
        }

    }

    private void promotion(Location from) throws CommandError {
        if (from.getRow()==7 || from.getRow()==0){
            System.out.println("\n You can make a promotion for your Pawn !!\n");
            System.out.println("\n Enter name of piece that you want to promote to: Queen, Knight, Rook, Bishop.");
            Scanner sc=new Scanner(System.in);
            String pro=sc.nextLine();
            if (pro.equals("Queen")){
                Queen newPiece=new Queen(from.getPiece().color,from,this);
                from.setPiece(newPiece);
            }
            else if (pro.equals("Knight")){
                Knight newPiece=new Knight(from.getPiece().color,from,this);
                from.setPiece(newPiece);
            }
            else if (pro.equals("Rook")) {
                Rook newPiece=new Rook(from.getPiece().color,from,this);
                from.setPiece(newPiece);
            }
            else if (pro.equals("Bishop")) {
                Bishop newPiece=new Bishop(from.getPiece().color,from,this);
                from.setPiece(newPiece);

            }
            else {
                throw new CommandError(CommandError.Wrong_Promotion);
            }

        }
    }

    private void movePieceCapturing(Location from, Location to)
    {
        Piece captured = getPieceAt(to);
        // End the game if King Captured
        if(captured.getName().equals("King"))
        {
            if (captured.color==Color.black)
            {
                System.out.println("=====White wins=====");
            }
            else
            {
                System.out.println("=====Black wins=====");
            }
            isKingCaptured=true;
        }
        if(captured.color == Color.black){
            bBoard.remove(captured);
        }else {
            wBoard.remove(captured);
        }
        captured.setLocation(null);
        to.setPiece(getPieceAt(from));
        from.setPiece(null);
    }

    private void movePieceWithoutCapturing(Location from, Location to)
    {
        to.setPiece(getPieceAt(from));
        from.setPiece(null);
    }

    public boolean isKingInCheck(boolean turn) throws MoveError {
        //turn true== black
        if(turn){
            for (Piece piece : wBoard) {
                if (piece.isValidMove(Black_King.getLocation())) {
                    isBKingCheck = true;
                    return true;
                }
            }
        }
        else{
            for (Piece piece : bBoard) {
                if (piece.isValidMove(White_King.getLocation())) {
                    isWKingCheck = true;
                    return true;
                }
            }
        }
        return false;

    }
    public boolean freeHorizontalPath(Location from, Location to) throws MoveError {
        //checking if horizontal in board locations is free
        if (from.getCol() < to.getCol()) {
            for (int i = from.getCol() + 1; i < to.getCol(); i++) {
                if (locations[from.getRow()][i].getPiece() != null)
                {
                    throw new MoveError(MoveError.Not_Free);
                }
            }
        }
        // constant row- decreasing column
        else if (from.getCol() > to.getCol())
            for (int i = to.getCol() + 1; i < from.getCol(); i++) {
                if (locations[from.getRow()][i].getPiece() != null)
                {
                    throw new MoveError(MoveError.Not_Free);
                }
            }

        return true;

    }

    public boolean freeVerticalPath(Location from, Location to) throws MoveError {
        //checking if vertical in board locations is free
        if (from.getRow() < to.getRow()) {
            for (int i = from.getRow() + 1; i < to.getRow(); i++) {
                if (locations[i][from.getCol()].getPiece() != null)
                {
                    throw new MoveError(MoveError.Not_Free);
                }
            }
        } else if (from.getRow() > to.getRow())
            for (int i = to.getRow() + 1; i < from.getRow(); i++) {
                if (locations[i][from.getCol()].getPiece() != null)
                {
                    System.out.println("B");
                    throw new MoveError(MoveError.Not_Free);
                }
            }

        return true;

    }

    public boolean freeAntiDiagonalPath(Location from, Location to) throws MoveError {
        int row;
        int col;
        //checking if Anti-Diagonal(like a Diagonal but opposite direction) in board locations is free
        if (from.getCol() < to.getCol()) {

            row = from.getRow() + 1;
            col = from.getCol() + 1;

            while (col < to.getCol()) {

                if (locations[row][col].getPiece() == null) {
                    row++;
                    col++;
                } else {
                    throw new MoveError(MoveError.Not_Free);
                }

            }
        } else {
            row = from.getRow() - 1;
            col = from.getCol() - 1;

            while (col > to.getCol()) {

                if (locations[row][col].getPiece() == null) {
                    row--;
                    col--;
                } else {
                    throw new MoveError(MoveError.Not_Free);
                }
            }

        }
        return true;
    }

    public boolean freeDiagonalPath(Location from, Location to) throws MoveError {
        //checking if Diagonal in board locations is free
        int row;
        int col;
        if (from.getCol() < to.getCol()) {

            row = from.getRow() - 1;
            col = from.getCol() + 1;

            while (col < to.getCol()) {

                if (locations[row][col].getPiece() == null) {
                    row--;
                    col++;
                } else {
                    throw new MoveError(MoveError.Not_Free);
                }

            }
        } else {
            row = from.getRow() + 1;
            col = from.getCol() - 1;

            while (col > to.getCol()) {

                if (locations[row][col].getPiece() == null) {
                    row++;
                    col--;
                } else {
                    throw new MoveError(MoveError.Not_Free);
                }
            }

        }
        return true;
    }

}
