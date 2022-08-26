package model;
import model.exceptions.*;

import java.util.List;
public class Board
{
    public  Location[][] locations;
    boolean isKingCaptured;
    boolean isKingCheck;
    Rook Black_Rook1,Black_Rook22,White_Rook1,White_Rook2;
    Knight Black_Knight1,Black_Knight2,White_Knigh1,White_Knigh2;
    Bishop Black_Bishop1,Black_Bishop2,White_Bishop1,White_Bishop2;
    Queen Black_Queen,White_Queen;
    King Black_King,White_King;
    Pawn []Black_Pawn;
    Pawn []White_Pawn;
    List<Piece> whiteCaptured;
    List<Piece> blackCaptured;
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
        isKingCheck=false;
    }

    public void init()
    {
        // BLACK
        Black_Rook1=new Rook(Color.black, locations[0][0], this);
        Black_Rook22=new Rook(Color.black, locations[0][7], this);
        Black_Knight1=new Knight(Color.black, locations[0][1], this);
        Black_Knight2=new Knight(Color.black, locations[0][6], this);
        Black_Bishop1=new Bishop(Color.black, locations[0][2], this);
        Black_Bishop2=new Bishop(Color.black, locations[0][5], this);
        Black_Queen=new Queen(Color.black, locations[0][3], this);
        Black_King=new King(Color.black, locations[0][4], this);
        for (int i = 0; i < 8; i++)
        {
            Black_Pawn[i]=new Pawn(Color.black, locations[1][i], this);
        }

        // WHITE
        White_Rook1= new Rook(Color.white, locations[7][0], this);
        White_Rook2=new Rook(Color.white, locations[7][7], this);
        White_Knigh1=new Knight(Color.white, locations[7][1], this);
        White_Knigh2=new Knight(Color.white, locations[7][6], this);
        White_Bishop1=new Bishop(Color.white, locations[7][2], this);
        White_Bishop2=new Bishop(Color.white, locations[7][5], this);
        White_Queen=new Queen(Color.white, locations[7][3], this);
        White_King= new King(Color.white, locations[7][4], this);
        for (int i = 0; i < 8; i++)
        {
            White_Pawn[i]=new Pawn(Color.white, locations[6][i], this);
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

    public void movePiece(Location from, Location to) throws MoveError
    {

        if (getPieceAt(to) == null) {
            movePieceWithoutCapturing(from, to);
        } else if (getPieceAt(from).color != getPieceAt(to).color) {
            movePieceCapturing(from, to);
        } else {
            throw new MoveError(MoveError.Not_Free);
        }

    }

    private void movePieceCapturing(Location from, Location to)
    {
        Piece captured = getPieceAt(to);
        if (captured.color == Color.black) {
            blackCaptured.add(captured);
        } else {
            whiteCaptured.add(captured);
        }

        if(captured.getName().equals("King"))
        {
            if (captured.color==Color.black)
            {
                System.out.println("White wins");
            }
            else
            {
                System.out.println("Black wins");
            }
            isKingCaptured=true;
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

    public boolean isKingCheck(boolean turn) throws MoveError {
        //turn true black
        if(turn){
            if(White_Rook1.isValidMove(Black_King.getLocation()) || White_Rook2.isValidMove(Black_King.getLocation()) ||  White_Knigh1.isValidMove(Black_King.getLocation()) ||  White_Knigh2.isValidMove(Black_King.getLocation()) || White_Bishop1.isValidMove(Black_King.getLocation()) || White_Bishop2.isValidMove(Black_King.getLocation()) || White_Queen.isValidMove(Black_King.getLocation())){
                return true;
            }
            for (int i = 0; i < 8; i++)
            {
                if(White_Pawn[i].isValidMove(Black_King.getLocation())) return true;
            }
           return false;
        }
        else{
            if(Black_Rook1.isValidMove(White_King.getLocation()) || Black_Rook22.isValidMove(White_King.getLocation()) ||  Black_Knight1.isValidMove(White_King.getLocation()) ||  Black_Knight2.isValidMove(White_King.getLocation()) || Black_Bishop1.isValidMove(White_King.getLocation()) || Black_Bishop2.isValidMove(White_King.getLocation()) || Black_Queen.isValidMove(White_King.getLocation())){
                return true;
            }
            for (int i = 0; i < 8; i++)
            {
                if(Black_Pawn[i].isValidMove(White_King.getLocation())) return true;
            }
            return false;
        }

    }
    public boolean freeHorizontalPath(Location from, Location to) throws MoveError {
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
    public int getWCaptured(){
        return whiteCaptured.size();
    }
    public int getBCaptured(){
        return blackCaptured.size();
    }

}
