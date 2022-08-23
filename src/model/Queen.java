package model;

import model.exceptions.MoveError;

public class Queen extends Piece{
    public Queen(Color color, Location location, Board board) {
        super(color, location, board,"Q");
    }
    @Override
    public void moveToLocation(Location newLocation) throws MoveError
    {
        if (isValidMove(newLocation)){
            board.movePiece(location,newLocation);
        }
        else {
            throw new MoveError(getName());
        }
    }

    @Override
    protected boolean isValidMove(Location To) throws MoveError {
        if (location.isDiagonalTo(To) && board.freeDiagonalPath(location, To))
        {
            return true;
        }
        else if (location.isAntiDiagonalTo(To) && board.freeAntiDiagonalPath(location, To))
        {
            return true;
        }
        else if (location.isHorizontalTo(To) && board.freeHorizontalPath(location, To))
        {
            return true;
        }
        else if (location.isVerticalTo(To) && board.freeVerticalPath(location, To))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location)
    {
        this.location=location;
    }

    @Override
    public String getName() {
        return "Queen";
    }
}
