


import java.io.IOException;


import org.jdom2.JDOMException;
import org.json.JSONException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class main extends Application {

	public static void main(String[] args )
	{
		Application.launch(args);
		
	}
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		Pane mainPane = (Pane) FXMLLoader.load(main.class.getResource("agentmew.fxml"));
		primaryStage.setTitle("ODIN V1.0");
		primaryStage.setScene(new Scene(mainPane));
	    primaryStage.show();
	  
		
}

}



