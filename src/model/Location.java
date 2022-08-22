package model;

public class Location {
    private int row;
    private int col;
    private Piece piece;

    public Location(String loc){
        char columnChar = loc.charAt(0);
        char rowChar = loc.charAt(1);
        row = rowChar - '0';
        row = 8 - row;
        col = columnChar - 'a';
    }

    public Location(int r, int c) {
        row=r;
        col=c;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece!=null){
            piece.setLocation(this);
        }
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
     }

    public Piece getPiece()
    {
        return piece;
    }

    public boolean isVerticalTo(Location to)
    {
        return this.col == to.col;
    }
    public boolean isHorizontalTo(Location to)
    {
        return this.row == to.row;
    }
    public boolean isAntiDiagonalTo(Location to)
    {
        return (this.row - to.row) == (this.col - to.col);
    }
    public boolean isDiagonalTo(Location to)
    {
        return (this.row + this.col) == (to.row + to.col);
    }

}
