package multifxmlapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Controller {
    
    private static final int FADE_DURATION = 2500;
    
    Pane                     container;
    Map<String, Pane>        screens;
    
    
    public Controller(Pane container) {
        this.container = container;
        screens = new HashMap<>();
    }
    
    
    public void switchContent(String fxml) {
        
       Pane mainScreen = getContent(fxml);
        
       mainScreen.setOpacity(0);
        
        if (!container.getChildren().isEmpty()) {            
            FadeTransition ft = fadeToOblivion(container.getChildren().get(0), FADE_DURATION);            
            ft.setOnFinished((actionEvent) -> {
                container.getChildren().clear();                
                container.getChildren().add(mainScreen);
                fadeToScene(container.getChildren().get(0), FADE_DURATION).play();
            });
            ft.play();
        } else {            
            container.getChildren().add(mainScreen);  
            fadeToScene(container.getChildren().get(0), FADE_DURATION).play();
        }
  
    }
    
    private Pane getContent(String fxml) {
        
        if (!screens.containsKey(fxml)) {
            Parent node = loadContent(fxml);
            screens.put(fxml,(Pane) node);
        }
        
        return screens.get(fxml);
    }
    
    
    private Parent loadContent(String fxml) {
        String path = String.format("%s.fxml", fxml);
        FXMLLoader loader = new FXMLLoader();
        
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(this.getClass().getResource(path));
        
        Parent page = null;
        
        try (InputStream in = this.getClass().getResourceAsStream(path)) {
            page = loader.load(in);
            ((Managed) loader.getController()).setScreensController(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
                
        return page;
    }
    
    
    private FadeTransition fadeToOblivion(Node node, double duration) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), node);
        ft.setToValue(0.0);        
        return ft;
    }
    
    
    private FadeTransition fadeToScene(Node node, double duration) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), node);
        ft.setToValue(1.0);        
        return ft;
    }
       
}
