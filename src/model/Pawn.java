package model;

import model.exceptions.MoveError;

public class Pawn extends Piece implements checkKing{
    public Pawn(Color color, Location location, Board board) {
        super(color, location, board,"P");
    }
    @Override
    public void moveToLocation(Location newLocation) throws MoveError {
        if (isValidMove(newLocation)){
            board.movePiece(location,newLocation);
        }
        else {
            throw new MoveError(getName());
        }
    }

    @Override
    protected boolean isValidMove(Location To) throws MoveError {
        if(isMoveFront(To)){
            if (To.getPiece()==null && isValidWhenNotCapturing(To)) return true;
            else if (To.getPiece()!=null && isValidWhenCapturing(To)) return true;
            else return false;
        }
        else
        {
            return false;
        }

    }

    private boolean isValidWhenCapturing(Location To) {
        int x=1;
        if (color==Color.white) x=-1;
        if (((location.getCol() - To.getCol() == 1) || (location.getCol() - To.getCol() == -1))
                && (location.getRow() - To.getRow() == x))
            return true;
        return false;
    }

    private boolean isValidWhenNotCapturing(Location To) throws MoveError {
        if (location.getCol() == To.getCol()) {
            if ((location.getRow() - To.getRow() == 1) || location.getRow() - To.getRow() == -1) {
                return true;
            }
            else if (((location.getRow() - To.getRow() == 2) || (location.getRow() - To.getRow() == -2))
                    && isTheFirstMove() && board.freeVerticalPath(location, To)) {
                return true;
            }
        }

        return false;
    }

    private boolean isTheFirstMove() {
        if (this.color.equals(Color.black) && location.getRow() == 1)
        {
            return true;
        }
        else if (this.color.equals(Color.white) && location.getRow() == 6) {
            return true;
        }
        return false;
    }

    private boolean isMoveFront(Location To) {
        if (this.color.equals(Color.black) && To.getRow() > location.getRow()) {
            return true;
        } else return this.color.equals(Color.white) && To.getRow() < location.getRow();
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location=location;
    }

    @Override
    public String getName() {
        return "Pawn";
    }

    @Override
    public boolean checkKing() {
        return false;
    }
}
