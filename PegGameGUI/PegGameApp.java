package PegGameGUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class PegGameApp extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException{
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the absolute path of the file: ");
        String filepath = scanner.nextLine();*/
        //scanner works but edited out for ease of use, hard code ur own path.
        String filepath="C:\\Users\\leenm\\uni work~\\SPRING23\\GCIS124\\CODES\\Activity 3\\Activity_3\\PegGameGUI\\board.txt";
        PegGameModel model=new PegGameModel(filepath); //pls add CLI file input code
        boolean[][] board=model.getBoard();
        
        PegGameView view=new PegGameView(board);

        new PegGameController(model,view);

        try {
            view.start(primaryStage);
        } catch (Exception e) {
            System.out.println("error");
        }
        //scanner.close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
