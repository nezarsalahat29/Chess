package model;

import model.exceptions.MoveError;

public class King extends Piece {

    public King(Color color, Location location, Board board) {
        super(color, location, board,"K");
    }

    @Override
    public void moveToLocation(Location newLocation) throws MoveError {
        if (isValidMove(newLocation)) {
            board.movePiece(location, newLocation);
        } else {
            throw new MoveError(getName());
        }
    }

    @Override
    protected boolean isValidMove(Location To) {
        if (location.isVerticalTo(To) && ((To.getRow() == location.getRow() + 1) || (To.getRow() == location.getRow() - 1)))
        {
            return true;
        }
        else if (location.isHorizontalTo(To) && ((To.getCol() == location.getCol() + 1) || (To.getCol() == location.getCol() - 1)))
        {
            return true;
        }
        else if (location.isAntiDiagonalTo(To) && ((To.getCol() == location.getCol() + 1) || (To.getCol() == location.getCol() - 1)))
        {
            return true;
        }
        else if (location.isDiagonalTo(To) && ((To.getCol() == location.getCol() + 1) || (To.getCol() == location.getCol() - 1)))
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
    public void setLocation(Location location) {
        this.location=location;
    }

    @Override
    public String getName() {
        return "King";
    }

}
