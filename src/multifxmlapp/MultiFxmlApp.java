package multifxmlapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MultiFxmlApp extends Application {
    
    static Controller controller;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setScene(new Scene(new StackPane()));
        
        controller = new Controller( (Pane) stage.getScene().getRoot());                                       
        
        controller.switchContent("first");
        
        stage.setResizable(false);
        
        stage.show();
        
    }


    public static void main(String[] args) {
        launch(args);
    }
    
    
}
