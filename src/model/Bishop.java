package model;

import model.exceptions.*;
public class Bishop extends Piece {

    public Bishop(Color color, Location location, Board board) {
        super(color, location, board,"B");
    }

    @Override
    public void moveToLocation(Location newLocation) throws MoveError {

    }

    @Override
    protected boolean isValidMove(Location To) {
        return false;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public void setLocation(Location location) {

    }

    @Override
    public String getName() {
        return "Bishop";
    }
}
