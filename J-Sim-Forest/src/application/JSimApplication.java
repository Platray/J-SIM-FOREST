package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JSimApplication extends Application {

	  @Override
	    public void start(Stage stage) {
		  	stage.setTitle("J SIM APPLICATION");
		  	//TAILLE FORET
		  	TextInputDialog dialog = new TextInputDialog("");
		  	dialog.setTitle("J SIM FOREST");
		  	dialog.setHeaderText(" Simulation configuration");
		  	dialog.setContentText("Please enter the size of the Forest:");

		  	Optional<String> result = dialog.showAndWait();
		  	if (result.isPresent()){
		  	int size =Integer.parseInt(result.get()) ;
		  	 
		  	 //DURATION
		  	TextInputDialog duration = new TextInputDialog("");
		  	duration.setTitle("J SIM FOREST");
		  	duration.setHeaderText(" Simulation configuration");
		  	duration.setContentText("Please enter the number of step :");

		  	Optional<String> timeDuration = duration.showAndWait();
		  	if (result.isPresent()){
		  	int durationInt = Integer.parseInt(timeDuration.get()) ;
		  	 MainView mainView = new MainView(size,durationInt);
		    Scene scene = new Scene(mainView, 480, 480);
	        stage.setResizable(false);
	        stage.setScene(scene);
	        stage.show();
	        mainView.draw();
		  	}	}else {
		  		MainView mainView =  new MainView(50,20);
		  	  
			}
	       

		  	
	     
	    }

	    public static void main(String[] args) {
	        launch();
	    }
}
