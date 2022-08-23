package model;

import model.exceptions.MoveError;

public class Knight extends Piece{
    public Knight(Color color, Location location, Board board) {
        super(color, location, board,"N");
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
    protected boolean isValidMove(Location To) {
        int twoStepBack = this.location.getRow() - 2;
        int twoStepFront = this.location.getRow() + 2;
        int twoStepRight = this.location.getCol() - 2;
        int twoStepLeft = this.location.getCol() + 2;
        int stepBack = this.location.getRow() - 1;
        int stepFront = this.location.getRow() + 1;
        int stepRight = this.location.getCol() - 1;
        int stepLeft = this.location.getCol() + 1;
        int toRow = To.getRow();
        int toCol = To.getCol();
        if (toRow==twoStepBack &&(toCol==stepLeft || toCol==stepRight))
        {
            return true;
        }
        else if (toRow==twoStepFront &&(toCol==stepLeft || toCol==stepRight))
        {
            return true;
        }
        else if (toCol==twoStepRight &&(toRow==stepFront || toRow==stepBack))
        {
            return true;
        }
        else if (toCol==twoStepLeft &&(toRow==stepFront || toRow==stepBack))
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

    }

    @Override
    public String getName() {
        return "Knight";
    }
}
