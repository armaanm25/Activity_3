package PegGameGUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class PegGameApp extends Application{
    @Override
    public void start(Stage primaryStage){
        PegGameModel model=new PegGameModel(filepath); //pls add CLI file input code
        PegGameView view=new PegGameView();

        new PegGameController(model,view);

        view.start(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
