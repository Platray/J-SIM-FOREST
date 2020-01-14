package application;

import controllers.GridForestController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import models.SimulationEntity;

public class JSimApplication extends Application {

	private SimulationEntity simulation;
	private int gridsize;
	private int cellsize;
	
	  @Override
	    public void init(){
	        System.out.println("In App Init!");
	        simulation = new SimulationEntity(gridsize, cellsize);
	    }
	
	@Override
	public void start(Stage primary) throws Exception {
		// TODO Auto-generated method stub
			BorderPane mainPane = new BorderPane();
	        mainPane.setPadding(new Insets(25, 25, 25, 25));
	        
	        FXMLLoader gridLoader = new FXMLLoader(getClass().getResource("ForestView.fxml"));
	        GridForestController gridController = new GridForestController(simulation);
	        gridLoader.setController(gridController);
	        Parent gridLayout = gridLoader.load();
	        mainPane.setCenter(gridLayout);

	}
	 public static void main(String[] args)
	    {
	        launch(args);
	    }
}
