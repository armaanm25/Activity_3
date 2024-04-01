package PegGameGUI;

/*
 * Move Class is used to represent where the Peg is being moved.
 * Start Point of the Peg and Destination of the Peg is used.
 */

public class Move {
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    //Creation of contructor
    public Move(int fromRow, int fromCol, int toRow, int toCol){
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }
    //Creation of Getters
    
    public int getFromRow() {
        return this.fromRow;
    }

    public int getFromCol() {
        return this.fromCol;
    }

    public int getToRow() {
        return this.toRow;
    }

    public int getToCol() {
        return this.toCol;
    }

    @Override
    public String toString() {
        return "(r" + (fromRow+1) + ", c" + (fromCol+1) + ") -> (r" + (toRow+1) + ", c" + (toCol+1) + ")";
    }
    

}
