package PegGameGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/* Design the graphical user interface (GUI) for displaying the game board and user interactions.
Create GUI components such as buttons, labels, and panels to represent the game elements.
Implement event listeners for mouse clicks to handle user input for selecting and moving pegs. */

public class PegGameView extends Application{

    public Label gamestateLabel;
    public int size;
    public int row;
    public int col;
    public boolean[][] board;
    public PegGameView(boolean[][] board){
        this.board=board;
    }
    private GridPane drawBoard(int size){
        // Draw grid
        GridPane gridPane=new GridPane();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Circle circle = new Circle(30); // Create a circle with radius 25
                //System.out.println(board[row][col]);
                if(board[col][row]==true){
                

                circle.setFill(Color.WHITE); // Set fill color
                circle.setStroke(Color.BLACK); // Set stroke color

                
                Button button = new Button(); // Create a button
                button.setGraphic(circle); 


                gridPane.add(button, row, col);}
                else{
                circle= new Circle(30);
                circle.setFill(Color.RED); // Set fill color
                circle.setStroke(Color.BLACK); // Set stroke color
                
                Button button = new Button(); // Create a button
                button.setGraphic(circle); 


                gridPane.add(button, row, col);
            }
                }
                }
            
            
        //filling out each button based on if its a peg or a hole

            return gridPane;
}

    public Button PegCreation(){
        Button pegButton=new Button();
        pegButton.setShape(new javafx.scene.shape.Circle(30));
        pegButton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        return pegButton;
    }

    public Button HoleCreation(){
        Button holeButton=new Button();
        holeButton.setShape(new javafx.scene.shape.Circle(10));
        holeButton.setBackground(new Background(new BackgroundFill(Color.CHOCOLATE,CornerRadii.EMPTY,Insets.EMPTY)));
        return holeButton;
    }
    private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {

        // Check if target is valid
        if (toRow < 0 || toRow > board.length || toCol < 0 || toCol >= board[0].length) {
            //System.out.println("FIRST error in isvalid");
            //System.out.println(holes[toRow][toCol]);
            //System.out.println(toRow);
            //System.out.println(holes.length);
            return false;
        }
        // Check  if from is valid
        if (fromRow < 0 || fromRow > board.length || fromCol < 0 || fromCol >= board[0].length) {
            //System.out.println("SECOND");
            return false;
        }
        // check midpoint
        int midRow = (fromRow + toRow) / 2;
        int midCol = (fromCol + toCol) / 2;
        if (midRow < 0 || midRow > board.length || midCol < 0 || midCol >= board[0].length) {
            //System.out.println("third");
            return false;
        }
        // Move is valid if the destination is empty and the midpoint has a peg
        try{
        if (board[toRow][toCol] == false && board[(fromRow + toRow) / 2][(fromCol + toCol) / 2]==true) {
            return true;
        }
    }catch (ArrayIndexOutOfBoundsException e){
        return false;
    }
        return board[(fromRow + toRow) / 2][(fromCol + toCol) / 2];
    }

    public GameState getGameState() {

        int pegNum = 0;
        int holeNum = 0;
        for (int i = 0; i < board.length; i++) {
            for (int x = 0; x < board[i].length; x++) {
                if (board[i][x]) {
                    pegNum++;
                } else {
                    holeNum++;
                }
            }
        }
        if (pegNum == 1) {
            return GameState.WON;
        }
        if (holeNum == 1) {
            return GameState.NOT_STARTED;
        }

        boolean noValidMoves = true;

        for (int i = 0; i < board.length; i++) {
            for (int x = 0; x < board[i].length; x++) {
                if (board[i][x]) {
                    if (isValidMove(i, x, i, x + 2) || isValidMove(i, x, i, x - 2) || isValidMove(i, x, i + 2, x)
                            || isValidMove(i, x, i - 2, x) || isValidMove(i, x, i + 2, x + 2)
                            || isValidMove(i, x, i + 2, x - 2) || isValidMove(i, x, i - 2, x - 2)
                            || isValidMove(i, x, i - 2, x + 2)) {
                        noValidMoves = false;
                        break;
                    }
                }
            }
            if (!noValidMoves) {
            break;
            }
        }
        if (noValidMoves) {
                return GameState.STALEMATE;
            }

        return GameState.IN_PROGRESS;
    }
    public String displayGameState(){
        //System.out.println(getGameState());
        GameState gameState= getGameState();  //we need to create a board instance
        System.out.println(gameState);
        String state;
        switch (gameState) {
            case NOT_STARTED:
                state= "Start the Game";
                break;
            case IN_PROGRESS:
                state= "In progress..";
                break;
            case STALEMATE:
                state= "You lost :(";
                break;
            case WON:
                state= "You won :)";
                break;
            default:
                state="something's up";
            
        }
        return state;
    }

    public void start(Stage stage) throws Exception{
        
        GridPane testpane= drawBoard(4);
        Label PegLabel=new Label("Peg Game!");
        PegLabel.setFont(Font.font("Helvetica",FontWeight.SEMI_BOLD,30));
        PegLabel.setTextFill(Color.BROWN);
        PegLabel.setAlignment(Pos.CENTER);

        Button closeButton=new Button("CLOSE");
        closeButton.setFont(Font.font("Helvetica",FontWeight.MEDIUM,15));
        closeButton.setTextFill(Color.RED);
        closeButton.setAlignment(Pos.BOTTOM_RIGHT);
        
        Label gamestateLabel = new Label(displayGameState());
        gamestateLabel.setFont(Font.font("Helvetica",FontWeight.SEMI_BOLD,20));
        gamestateLabel.setTextFill(Color.BROWN);
        gamestateLabel.setAlignment(Pos.TOP_RIGHT);

        stage.setTitle("Peg Game App");
        HBox h1=new HBox();
        h1.getChildren().addAll(PegLabel,gamestateLabel);
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(20);

        testpane.setAlignment(Pos.CENTER);
        VBox v1=new VBox();
        v1.getChildren().addAll(h1,testpane,closeButton);
        v1.setAlignment(Pos.CENTER);
        v1.setSpacing(20);

        Scene scene=new Scene(v1,500,400);
        stage.setScene(scene);   
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
