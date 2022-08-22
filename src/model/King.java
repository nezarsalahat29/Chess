package model;

import model.exceptions.MoveError;

public class King extends Piece {

    public King(Color color, Location location, Board board) {
        super(color, location, board,"K");
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
        return "King";
    }
}
