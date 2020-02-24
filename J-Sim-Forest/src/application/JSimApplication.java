package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JSimApplication extends Application {

	  @Override
	    public void start(Stage stage) {
		  	stage.setTitle("J SIM APPLICATION");
	        MainView mainView = new MainView();
	        Scene scene = new Scene(mainView, 480, 480);
	        stage.setResizable(false);
	        stage.setScene(scene);
	        stage.show();
	        mainView.draw();
	    }

	    public static void main(String[] args) {
	        launch();
	    }
}
