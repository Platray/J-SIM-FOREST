package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import models.SimulationEntity;

public class GridForestController implements Initializable {

	private SimulationEntity simulation;
	private int gridsize;
	private boolean previous;

	@FXML
	private GridPane gridPane;

	@FXML
	private Label gameSpeedLabelValue;

	public GridForestController(SimulationEntity simulation) {
		this.simulation = simulation;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initializeGridpane();

		
	}

	private void initializeGridpane() {
		int gridsize = this.simulation.getGridSize();
		int cellsize = this.simulation.getCellsize();

		for (int i = 0; i < gridsize; i++) {
			gridPane.getColumnConstraints().add(new ColumnConstraints(cellsize));
			gridPane.getRowConstraints().add(new RowConstraints(cellsize));

		}
		for (int i = 0; i < gridsize; i++) {
			for (int j = 0; j < gridsize; j++) {
				initializeCells(i, j);
			}
		}

	}

	private void initializeCells(int row, int col) {
		int cellSize = simulation.getCellsize();

		Rectangle cellRect = new Rectangle();
		cellRect.setHeight(cellSize);
		cellRect.setWidth(cellSize);
		cellRect.setFill(Color.WHITE);
		cellRect.setStroke(Color.web("#F6F6F6"));
		cellRect.setStrokeType(StrokeType.INSIDE);
		cellRect.setStrokeWidth(0.5);
		cellRect.setSmooth(true);

	};

}
