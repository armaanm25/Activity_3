package PegGameGUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


/* Develop the controller logic to coordinate interactions between the View and Model components.
Implement methods for responding to user input events and updating the Model accordingly.
Ensure proper error handling and validation of user actions, such as checking for legal moves. */

public class PegGameController implements EventHandler<ActionEvent>{
    private PegGameModel model;
    private PegGameView view;
    public boolean[][] board;
    public String Button;
    public int fromRow=99;
    public int fromCol;

    //constructor
    public PegGameController(String Button){
        this.Button=Button;
    }
    public PegGameController(String Button,PegGameView view){
        this.Button=Button;
        this.view=view;
    }
    @Override
    public void handle(ActionEvent event) {
        switch (Button) {
            case "close":
                Platform.exit();
                break;
            case "save":
                System.out.println("Game is saved");
                Platform.exit();
                break;
            case "move":
            if (fromRow == 99){
            Button button = (Button) event.getSource();
            int[] position = (int[]) button.getUserData();
            fromRow = position[0];
            fromCol = position[1];
            System.out.println("Button at row=" + fromRow + ", column=" + fromCol + " is clicked.");
            }
            else{
            
            Button button = (Button) event.getSource();
            int[] position = (int[]) button.getUserData();
            int toRow = position[0];
            int toCol = position[1];
            System.out.println("Second Button at row=" + toRow + ", column=" + toCol + " is clicked.");
            PegGameApp.getmodel().makeMove(fromRow,fromCol, toRow, toCol);
            }

                //PegGameApp.getview().Jumper();
            System.out.println("Pressed1");
            
                break;
            default:
                break;
        }
    }
}
