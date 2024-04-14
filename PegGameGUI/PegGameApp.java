package PegGameGUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class PegGameApp extends Application{
    public static PegGameView view;
    public static PegGameModel model;
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the absolute path of the file: ");
        String filepath = scanner.nextLine();*/
        //scanner works but edited out for ease of use, hard code ur own path.
        String filepath="C:/Users/ender/Desktop/koko/Activity_3/PegGameGUI/board.txt";
        
            
                /*model=new PegGameModel(filepath);
                boolean[][] board=model.getBoard();
                view=new PegGameView(board);
                GameState gameState = model.getGameState();
                view.start(primaryStage);
                if (gameState == GameState.WON) {
                    System.out.println("Congratulations! You won!");
                    
                } else if (gameState == GameState.STALEMATE) {
                    System.out.println("Game over. Stalemate!");
                    
                }
        }catch (IOException e) {
            System.out.println("Enter a Valid File Path");
        }*/
        
        
        
        
        
        
        
        
        
        model=new PegGameModel(filepath); //pls add CLI file input code
        boolean[][] board=model.getBoard();

        view=new PegGameView(board);
        
        

        try {
            view.start(primaryStage);
        } catch (Exception e) {
            System.out.println("error");
        }
        //scanner.close();
    }
    public static PegGameView getview(){
        System.out.println("returning");
        return view;
    }    public static PegGameModel getmodel(){
        System.out.println("returning1");
        return model;
    }
    public static void main(String[] args) {
        launch(args);
    }
}

 