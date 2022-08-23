package model;

import model.exceptions.MoveError;

public abstract class Piece {
    protected Color color;
    protected Location location;
    protected Board board;
    protected String name;
    public Piece(Color color, Location location, Board board , String name) {
        this.color = color;
        this.location = location;
        this.location.setPiece(this);
        this.board = board;
        this.name=name;
    }
    public abstract void moveToLocation(Location newLocation) throws MoveError;
    protected abstract boolean isValidMove(Location To) throws MoveError;
    public abstract Location getLocation();
    public abstract void setLocation(Location location);
    public abstract String getName();
}
