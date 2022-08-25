package model;
import model.exceptions.*;
public class Board
{
    public  Location[][] locations;
    boolean isKingCaptured;
    boolean isKingCheck;
    Rook BR1,BR2,WR1,WR2;
    Knight BN1,BN2,WN1,WN2;
    Bishop BB1,BB2,WB1,WB2;
    Queen BQ,WQ;
    King BK,WK;
    Pawn []BP;
    Pawn []WP;
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
        BP=new Pawn[8];
        WP=new Pawn[8];
        isKingCaptured = false;
        isKingCheck=false;
    }

    public void init()
    {
        // BLACK
        BR1=new Rook(Color.black, locations[0][0], this);
        BR2=new Rook(Color.black, locations[0][7], this);
        BN1=new Knight(Color.black, locations[0][1], this);
        BN2=new Knight(Color.black, locations[0][6], this);
        BB1=new Bishop(Color.black, locations[0][2], this);
        BB2=new Bishop(Color.black, locations[0][5], this);
        BQ=new Queen(Color.black, locations[0][3], this);
        BK=new King(Color.black, locations[0][4], this);
        //BP[0]=new Pawn(Color.black,locations[1][0],this);
        for (int i = 0; i < 8; i++)
        {
            BP[i]=new Pawn(Color.black, locations[1][i], this);
        }

        // WHITE
        WR1= new Rook(Color.white, locations[7][0], this);
        WR2=new Rook(Color.white, locations[7][7], this);
        WN1=new Knight(Color.white, locations[7][1], this);
        WN2=new Knight(Color.white, locations[7][6], this);
        WB1=new Bishop(Color.white, locations[7][2], this);
        WB2=new Bishop(Color.white, locations[7][5], this);
        WQ=new Queen(Color.white, locations[7][3], this);
        WK= new King(Color.white, locations[7][4], this);
        //WP[0]=new Pawn(Color.black,locations[1][0],this);
        for (int i = 0; i < 8; i++)
        {
            WP[i]=new Pawn(Color.white, locations[6][i], this);
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
            if(WR1.isValidMove(BK.getLocation()) || WR2.isValidMove(BK.getLocation()) ||  WN1.isValidMove(BK.getLocation()) ||  WN2.isValidMove(BK.getLocation()) || WB1.isValidMove(BK.getLocation()) || WB2.isValidMove(BK.getLocation()) || WQ.isValidMove(BK.getLocation())){
                return true;
            }
            for (int i = 0; i < 8; i++)
            {
                if(WP[i].isValidMove(BK.getLocation())) return true;
            }
           return false;
        }
        else{
            if(BR1.isValidMove(WK.getLocation()) || BR2.isValidMove(WK.getLocation()) ||  BN1.isValidMove(WK.getLocation()) ||  BN2.isValidMove(WK.getLocation()) || BB1.isValidMove(WK.getLocation()) || BB2.isValidMove(WK.getLocation()) || BQ.isValidMove(WK.getLocation())){
                return true;
            }
            for (int i = 0; i < 8; i++)
            {
                if(BP[i].isValidMove(WK.getLocation())) return true;
            }
            return false;
        }

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
