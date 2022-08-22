package model;

import model.exceptions.MoveError;

public class Rook extends Piece{
    public Rook(Color color, Location location, Board board) {
        super( color,  location,  board,"R");
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
        return "Rook";
    }
}
