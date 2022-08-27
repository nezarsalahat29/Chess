package model.exceptions;

public class MoveError extends Exception {
    public static final String No_Piece="\n No piece in location\n";

    public static final String King_Is_Check="\n Try Again!\n";
    public static final String NOT_YOUR_PIECE = "\n You tried to move your opponent's piece.\n";
    public static final String Piece_Invalid_Move= "\n Invalid Move\n";
    public static final String Not_Free="\n Not free\n";
    public static final String KING = "\n! ! ! The king can only move one square in any direction - up, down, to the sides, and diagonally.\n";

    public static final String QUEEN = "\n! ! ! The queen can move in any one straight direction - "
            + "\n! ! ! forward, backward, sideways, or diagonally - as far as possible "
            + "\n! ! ! as long as she does not move through any of her own pieces.\n";

    public static final String BISHOP = "\n Invalid Move\n Bishop move only diagonally.\n";

    public static final String ROOK = "\n!\n Invalid Move\n Rook move only forward, backward, and to the sides.\n";

    public static final String KNIGHT = "\n Invalid Move\n Knight can move two squares in one direction, "
            + "and then one more move at a 90 degree angle, just like the shape of an <L>\n";
    public static final String PAWN = "\n! ! ! Pawns move and capture in different ways: they move forward, but capture one square diagonally in front of them. "
            + "\n! ! ! Pawns can only move forward one square at a time, "
            + "\n! ! ! except for their very first move where they can move forward two squares. "
            + "\n! ! ! If there is another piece directly in front of a pawn, it cannot move past or capture that piece.\n";
    public static final String ChangeTurn="\nChange turn\n";

    public MoveError(String s){
        super(s);
    }

}
