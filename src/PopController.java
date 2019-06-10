import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class PopController  {

    @FXML
    private Button ClosePopup;
    
    @FXML
    private Label PopUpText;

    @FXML
    void onButtonClock(ActionEvent event) {
    	Stage stage = (Stage) ClosePopup.getScene().getWindow();
    stage.close();
    }
    
    
    public void Popuptext(String text) {
    	this.PopUpText.setText(text);
	
 }
      
}

  


