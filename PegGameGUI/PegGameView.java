package PegGameGUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
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
    public GridPane gridPane;
    public Label gamestateLabel;
    public int size;
    public int row;
    public int col;
    public boolean[][] board;
    public int fromRow;
    public int fromCol;
    private PegGameController gridbutton= new PegGameController("move");
    public PegGameView(boolean[][] board){
        this.board=board;
    }
    private Button addButtonToGrid(GridPane gridPane,String ButtonID){
        Circle circle = new Circle(30); // Create a circle with radius 25
        circle.setFill(Color.MISTYROSE); // Set fill color
        circle.setStroke(Color.LIGHTPINK); // Set stroke color
        
        Button button = new Button();
        button.setUserData(new int[]{row, col});
        button.setGraphic(circle);

        //PegGameController gridbutton= new PegGameController("move");
        button.setOnAction(gridbutton);
        return button;
    }
    public void Jumper(){
        
        
        if(board[col][row]=true){
            
        }
    }
    private GridPane drawBoard(int size){
        // Draw grid
        gridPane=new GridPane();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Circle circle = new Circle(30); // Create a circle with radius 25
                //System.out.println(board[row][col]);
                if(board[col][row]==true){
                Button button = new Button();
                System.out.println("col in Grid:"+col);
                button.setUserData(new int[]{col,row});
                button.setOnAction(gridbutton);



                circle.setFill(Color.MISTYROSE); // Set fill color
                circle.setStroke(Color.LIGHTPINK); // Set stroke color

                
                //Button button = new Button(); // Create a button
                //String ButtonId= String.valueOf(col)+String.valueOf(row);
                //System.out.println(ButtonId);
                //Button button= addButtonToGrid(gridPane,ButtonId);
                button.setGraphic(circle);
                
                

                
                        // Load the image
        Image image = new Image("file:board.png");

        // Set the background image of the button
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        Background background = new Background(backgroundImage);
                    button.setBackground(background);


                gridPane.add(button, row, col);}
                else{
                circle= new Circle(30);
                circle.setFill(Color.DARKGRAY); // Set fill color
                circle.setStroke(Color.GRAY); // Set stroke color
                System.out.println("col in Grid:"+col);
                Button button = new Button("Button " + (row * 3 + col + 1));
                button.setUserData(new int[]{col,row});
                button.setOnAction(gridbutton);

                button.setGraphic(circle); 
            

                // Load the image
        Image image = new Image("file:board.png");

        // Set the background image of the button
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        Background background = new Background(backgroundImage);
                    button.setBackground(background);

                gridPane.add(button, row, col);
            }
                }
                }
            
            
        //filling out each button based on if its a peg or a hole

            return gridPane;
}

    public Button PegCreation(){
        Button pegButton=new Button();
        pegButton.setShape(new javafx.scene.shape.Circle(15));
        pegButton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        pegButton.setPrefSize(30, 30);
        return pegButton;
    }

    public Button HoleCreation(){
        Button holeButton=new Button();
        holeButton.setShape(new javafx.scene.shape.Circle(15));
        holeButton.setBackground(new Background(new BackgroundFill(Color.CHOCOLATE,CornerRadii.EMPTY,Insets.EMPTY)));
        holeButton.setPrefSize(30, 30);
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
        /*Label PegLabel=new Label("Peg Game!");
        PegLabel.setFont(Font.font("Helvetica",FontWeight.SEMI_BOLD,30));
        PegLabel.setTextFill(Color.BROWN);
        PegLabel.setAlignment(Pos.CENTER);*/

        Button closeButton=new Button("CLOSE");
        PegGameController closeButtonHandler= new PegGameController("close");
        closeButton.setOnAction(closeButtonHandler);
        closeButton.setFont(Font.font("Arial",FontWeight.BOLD,15));
        closeButton.setTextFill(Color.DARKOLIVEGREEN);
        closeButton.setAlignment(Pos.BOTTOM_RIGHT);
        
        Label gamestateLabel = new Label(displayGameState());
        gamestateLabel.setFont(Font.font("Sans Serif",FontWeight.BOLD,20));
        gamestateLabel.setTextFill(Color.DARKSEAGREEN);
        gamestateLabel.setAlignment(Pos.TOP_RIGHT);

        stage.setTitle("Peg Game~");
        stage.getIcons().add(new Image("File:C:/Users/ender/Desktop/koko/Activity_3/PegGameGUI/icon.png"));

        /*VBox v1=new VBox();
        v1.getChildren().addAll(gamestateLabel);
        v1.setAlignment(Pos.TOP_RIGHT);
        v1.setSpacing(20);

        HBox h1=new HBox();
        h1.getChildren().addAll(testpane,v1);
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(20);*/

        VBox v2=new VBox();
        v2.getChildren().addAll(gamestateLabel,testpane,closeButton);
        v2.setAlignment(Pos.CENTER);
        v2.setSpacing(20);

        testpane.setAlignment(Pos.CENTER);
        
        Image backgroundImage = new Image("File:C:/Users/ender/Desktop/koko/Activity_3/PegGameGUI/kittens.png");
        BackgroundImage background = new BackgroundImage(
            backgroundImage,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
            v2.setBackground(new Background(background));

        Scene scene=new Scene(v2,500,400);
        stage.setScene(scene);   
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
