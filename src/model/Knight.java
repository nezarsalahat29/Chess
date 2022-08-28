package model;

import model.exceptions.CommandError;
import model.exceptions.MoveError;

public class Knight extends Piece {
    public Knight(Color color, Location location, Board board) {
        super(color, location, board,"N");
    }

    @Override
    public void moveToLocation(Location newLocation) throws MoveError, CommandError {
        if (isValidMove(newLocation)){
            board.movePiece(location,newLocation);
        }
        else {
            throw new MoveError(MoveError.KNIGHT);
        }
    }

    @Override
    protected boolean isValidMove(Location To) throws MoveError {

        Location l1 = new Location(location.getRow()+2,location.getCol()+1);
        Location l2 = new Location(location.getRow()+2,location.getCol()-1);
        Location l3 = new Location(location.getRow()-2,location.getCol()+1);
        Location l4 = new Location(location.getRow()-2,location.getCol()-1);
        Location l5 = new Location(location.getRow()+1,location.getCol()-2);
        Location l6 = new Location(location.getRow()+1,location.getCol()+2);
        Location l7 = new Location(location.getRow()-1,location.getCol()+2);
        Location l8 = new Location(location.getRow()-1,location.getCol()-2);

        if (l1.getRow()== To.getRow() && l1.getCol()== To.getCol()) return true;
        else if (l2.getRow()== To.getRow() && l2.getCol()== To.getCol()) return true;
        else if (l3.getRow()== To.getRow() && l3.getCol()== To.getCol()) return true;
        else if (l4.getRow()== To.getRow() && l4.getCol()== To.getCol()) return true;
        else if (l5.getRow()== To.getRow() && l5.getCol()== To.getCol()) return true;
        else if (l6.getRow()== To.getRow() && l6.getCol()== To.getCol()) return true;
        else if (l7.getRow()== To.getRow() && l7.getCol()== To.getCol()) return true;
        else if (l8.getRow()== To.getRow() && l8.getCol()== To.getCol()) return true;
        else return false;

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
        return "Knight";
    }


}
