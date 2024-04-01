package PegGameGUI;

/* 
 * Location Class represents current position of the Peg.
 * Row and Column is used to represent it.
 */

public class Location {
    protected int row;
    protected int col;
    //Creation of Constructor
    public Location(int row, int col){
        this.row=row;
        this.col=col;
    }
    //Creation of Getters
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString(){
        return "(" + row + ", " + "col" + ")";
    }
}
