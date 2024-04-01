package PegGameGUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class View extends Application{
    public Label gamestateLabel;
    public int size;
    public int row;
    public int col;
    private Node gridPane; 
    

    private void drawBoard(GridPane gridPane,int size){

        GridPane gridPane=new GridPane();
        // Draw grid
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Circle circle = new Circle(30); // Create a circle with radius 25
                circle.setFill(Color.WHITE); // Set fill color
                circle.setStroke(Color.BLACK); // Set stroke color

                
                Button button = new Button(); // Create a button
                button.setGraphic(circle); 

                gridPane.add(button, row, col);
                }
            }
        }

    /*public void displayGameState(){
        GameState gameState=game.getGameState();
        switch (gameState) {
            case NOT_STARTED:
                gamestateLabel.setText("Start the Game");
            case IN_PROGRESS:
                gamestateLabel.setText("In progress..");
            case STALEMATE:
                gamestateLabel.setText("You lost :(");
            case WON:
                gamestateLabel.setText("You won :)");
            
        }
    }*/

    public void start(Stage stage) throws Exception{
        Label PegLabel=new Label("Peg Game!");
        PegLabel.setFont(Font.font("Helvetica",FontWeight.SEMI_BOLD,30));
        PegLabel.setTextFill(Color.INDIGO);
        PegLabel.setAlignment(Pos.CENTER);

        Button closeButton=new Button("CLOSE");
        closeButton.setFont(Font.font("Helvetica",FontWeight.MEDIUM,15));
        closeButton.setTextFill(Color.RED);
        closeButton.setAlignment(Pos.BOTTOM_RIGHT);

        stage.setTitle("Peg Game App");
        VBox v1=new VBox();
        v1.getChildren().addAll(PegLabel,gamestateLabel,gridPane,closeButton);
        v1.setAlignment(Pos.CENTER);
        v1.setSpacing(20);

        //Scene scene=new Scene(gridPane,400,300);
        //stage.setScene(scene);
        stage.setScene(new Scene(v1));   //parameter for Scene might be different
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
