package multifxmlapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FirstFXMLController implements Initializable , Managed {
    
    Controller controller ;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        controller.switchContent("second");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @Override
    public void setScreensController(Controller controller) {
        this.controller = controller;
    }    
    
}
