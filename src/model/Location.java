package model;

public class Location {
    private int row;
    private int col;
    private Piece piece;

    public Location(String loc){
        //		a->0		'1'->1->7
        //		b->1		'2'->2->6
        //		c->2		'3'->3->5
        //		d->3		'4'->4->4
        //		e->4		'5'->5->3
        //		f->5		'6'->6->2
        //		g->6		'7'->7->1
        //		h->7		'8'->8->0
        //because it is 8x8 board so it is 2 dimension array of locations
        //the last row in chess is first one in (8x8 locations array)=board
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
