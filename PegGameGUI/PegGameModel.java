package PegGameGUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/* This class define methods for representing the game board, pegs, and game logic.
Implement methods for loading the initial board setup from a file and updating the board state based on user moves.
Ensure the Model classes follow the MVC pattern and encapsulate the game's state and behavior. */

public class PegGameModel {
    private boolean[][] board;

    public PegGameModel(String filePath) throws IOException {
        this.board = initializeBoard(filePath);
    }

    // Loads the initial board setup from the specified file
    private boolean[][] initializeBoard(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int size = Integer.parseInt(reader.readLine());
        boolean[][] board = new boolean[size][size];
        String line;
        int row = 0;
        System.out.println("hi");
        while ((line = reader.readLine()) != null && row < size) {
            for (int col = 0; col < size; col++) {
                char ch = line.charAt(col);
                if (ch == '0') {
                    board[row][col] = true;
                } else {
                    board[row][col] = false;
                }
            }
            row++;
        }
        reader.close();
        for (boolean[] row1 : board) {
            for (boolean value : row1) {
                System.out.print(value+"-");
            }
            System.out.println();
        }

        return board;
    }

    // Returns the board as a boolean 2D array
    public boolean[][] getBoard() {
        return board;
    }

    // Calculates and returns a collection of possible moves based on the current state of the board
    public Collection<Move> getPossibleMoves() {
        Collection<Move> possibleMoves = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col]) {
                    checkMove(row, col, row + 2, col, possibleMoves);
                    checkMove(row, col, row - 2, col, possibleMoves);
                    checkMove(row, col, row, col + 2, possibleMoves);
                    checkMove(row, col, row, col - 2, possibleMoves);
                    checkMove(row, col, row + 2, col + 2, possibleMoves);
                    checkMove(row, col, row + 2, col - 2, possibleMoves);
                    checkMove(row, col, row - 2, col + 2, possibleMoves);
                    checkMove(row, col, row - 2, col - 2, possibleMoves);
                }
            }
        }
        return possibleMoves;
    }

    // Checks if a move from the specified position to the target position is valid and adds it to the collection of possible moves
    private void checkMove(int fromRow, int fromCol, int toRow, int toCol, Collection<Move> possibleMoves) {
        int jumpedRow = (fromRow + toRow) / 2;
        int jumpedCol = (fromCol + toCol) / 2;

        if (isValidPosition(toRow, toCol) && !board[toRow][toCol] && board[jumpedRow][jumpedCol]) {
            possibleMoves.add(new Move(fromRow, fromCol, toRow, toCol));
        }
    }
    
    // Checks if a move from the specified position to the target position is valid without adding it to a collection
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        int jumpedRow = (fromRow + toRow) / 2;
        int jumpedCol = (fromCol + toCol) / 2;

        return isValidPosition(toRow, toCol) && !board[toRow][toCol] && board[jumpedRow][jumpedCol];
    }

    // Checks if the specified position is a valid position on the board
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
    }

    // Makes a move from the specified position to the target position on the board
    public void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (isValidMove(fromRow, fromCol, toRow, toCol)) {
            int jumpedRow = (fromRow + toRow) / 2;
            int jumpedCol = (fromCol + toCol) / 2;

            board[fromRow][fromCol] = false;
            board[jumpedRow][jumpedCol] = false;
            board[toRow][toCol] = true;
        } else {
            System.out.println("Invalid move!");
        }
    }

    // Determines the current state of the game
    public GameState getGameState() {
        int pegCount = 0;
        int holeCount = 0;

        for (boolean[] row : board) {
            for (boolean peg : row) {
                if (peg) {
                    pegCount++;
                } else {
                    holeCount++;
                }
            }
        }
        if (holeCount == 1) {
            return GameState.NOT_STARTED;
        } else if (pegCount == 1) {
            return GameState.WON;
        } else if (pegCount > 1 && !getPossibleMoves().isEmpty()) {
            return GameState.IN_PROGRESS;
        } else {
            return GameState.STALEMATE;
        }
    }
}
