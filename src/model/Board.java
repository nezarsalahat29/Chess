package model;
import java.util.ArrayList;
import java.util.List;

import model.exceptions.*;
public class Board
{
    public  Location[][] locations;
    List<Piece> whiteCaptured;
    List<Piece> blackCaptured;
    boolean isKingCaptured;
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
        whiteCaptured = new ArrayList<>();
        blackCaptured = new ArrayList<>();
        isKingCaptured = false;
    }

    public void init()
    {
        // BLACK
        new Rook(Color.black, locations[0][0], this);
        new Rook(Color.black, locations[0][7], this);
        new Knight(Color.black, locations[0][1], this);
        new Knight(Color.black, locations[0][6], this);
        new Bishop(Color.black, locations[0][2], this);
        new Bishop(Color.black, locations[0][5], this);
        new Queen(Color.black, locations[0][3], this);
        new King(Color.black, locations[0][4], this);
        for (int i = 0; i < 8; i++)
        {
            new Pawn(Color.black, locations[1][i], this);
        }

        // WHITE
        new Rook(Color.white, locations[7][0], this);
        new Rook(Color.white, locations[7][7], this);
        new Knight(Color.white, locations[7][1], this);
        new Knight(Color.white, locations[7][6], this);
        new Bishop(Color.white, locations[7][2], this);
        new Bishop(Color.white, locations[7][5], this);
        new Queen(Color.white, locations[7][3], this);
        new King(Color.white, locations[7][4], this);
        for (int i = 0; i < 8; i++)
        {
            new Pawn(Color.white, locations[6][i], this);
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
            throw new MoveError("not free");
        }
    }

    private void movePieceCapturing(Location from, Location to)
    {
        Piece captured = getPieceAt(to);
        if (captured.color == Color.black)
        {
            blackCaptured.add(captured);
        }
        else
        {
            whiteCaptured.add(captured);
        }
        if(captured.getName().equals("K"))
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
    public boolean freeHorizontalPath(Location from, Location to) throws MoveError {
        if (from.getCol() < to.getCol()) {
            for (int i = from.getCol() + 1; i < to.getCol(); i++) {
                if (locations[from.getRow()][i].getPiece() != null)
                {
                    throw new MoveError("not free");
                }
            }
        }
        // constant row- decreasing column
        else if (from.getCol() > to.getCol())
            for (int i = to.getCol() + 1; i < from.getCol(); i++) {
                if (locations[from.getRow()][i].getPiece() != null)
                {
                    throw new MoveError("not free");
                }
            }

        return true;

    }

    public boolean freeVerticalPath(Location from, Location to) throws MoveError {
        if (from.getRow() < to.getRow()) {
            for (int i = from.getRow() + 1; i < to.getRow(); i++) {
                if (locations[i][from.getCol()].getPiece() != null)
                {
                    throw new MoveError("not free");
                }
            }
        } else if (from.getRow() > to.getRow())
            for (int i = to.getRow() + 1; i < from.getRow(); i++) {
                if (locations[i][from.getCol()].getPiece() != null)
                {
                    System.out.println("B");
                    throw new MoveError("not free");
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
                    throw new MoveError("not free");
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
                    throw new MoveError("not free");
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
                    throw new MoveError("not free");
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
                    throw new MoveError("not free");
                }
            }

        }
        return true;
    }

}
