package model;

import model.exceptions.CommandError;
import model.exceptions.MoveError;

public abstract class Piece {
    protected Color color;
    protected Location location;
    protected Board board;
    protected String name;
    protected boolean checkKing;
    public Piece(Color color, Location location, Board board , String name) {
        this.color = color;
        this.location = location;
        this.location.setPiece(this);
        this.board = board;
        this.name=name;
        checkKing=false;
    }
    public abstract void moveToLocation(Location newLocation) throws MoveError, CommandError;
    protected abstract boolean isValidMove(Location To) throws MoveError;
    public abstract Location getLocation();
    public abstract void setLocation(Location location);
    public abstract String getName();
}
