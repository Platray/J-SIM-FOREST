package application;

import controllers.GridForestController;
import controllers.PanelController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import models.SimulationEntity;

public class JSimApplication extends Application {

	private SimulationEntity simulation;
	private int gridsize;
	private int cellsize;

	@Override
	public void init() {
		System.out.println(" App Init!");
		simulation = new SimulationEntity(75, 8);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Root
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(new Insets(25, 25, 25, 25));

		FXMLLoader gridLoader = new FXMLLoader(getClass().getResource("ForestView.fxml"));
		GridForestController gridController = new GridForestController(simulation);

		gridLoader.setController(gridController);
		Parent gridLayout = gridLoader.load();

		// Ajout du gridlayout au mainpane
		mainPane.setCenter(gridLayout);
		
        FXMLLoader controlLoader = new FXMLLoader(getClass().getResource("ControlPanel.fxml"));

	    // Create and attach an instance of the ControllerGameBoard to the loader.
        PanelController panelController = new PanelController(simulation);
        controlLoader.setController(panelController);
        Parent controlPanelLayout = controlLoader.load();
        
        // Add the gridLayout to the main pane.
        mainPane.setRight(controlPanelLayout);
		// Creation d'une scene et ajout du main pane.
		Scene mainScene = new Scene(mainPane, 855, 675);
		// mise en place stage.
		stage.setScene(mainScene);
		stage.setResizable(false);
		stage.setTitle("J SIM FOREST");
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
