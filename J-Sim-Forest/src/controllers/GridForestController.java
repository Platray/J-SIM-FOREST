package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.CellState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.LongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import models.CellEntity;
import models.SimulationEntity;

public class GridForestController implements Initializable {

	private int clickType;
	private int counter = 0;
	private SimulationEntity simulation;

	@FXML
	private GridPane gridPane;
	@FXML
	private Label gameSpeedLabelValue;
	@FXML
	private Label generationLabelValue;
	@FXML
	private Label gameStateLabelValue;
	@FXML
	private Label ruleSetLabelValue;

	@FXML
	private TextField width;

	@FXML
	private TextField height;

	public GridForestController(SimulationEntity simulation) {
		this.simulation = simulation;
	}

	@Override
	public void initialize(URL location, ResourceBundle ressources) {

		// grille
		initializeGridpane();

		simulation.gameRunningProperty().addListener(gameRunningListener());

		// generation
		simulation.generationProperty().addListener(generationInvalidationListener());

		// Trigger le raffraichissement
		simulation.needsRedrawProperty().addListener(needsRefreshChangeListener());
	}
	// LES LISTENERS pour être à l'écoute de la forêt:

	private ChangeListener<Boolean> needsRefreshChangeListener() {
		return new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				simulation.needsRedrawProperty().removeListener(this);
				updateGridRectangles();
				simulation.setNeedsRedraw(false);
				simulation.needsRedrawProperty().addListener(this);
				System.out.println("MISE A JOUR ");
			}

		};

	}

	private InvalidationListener generationInvalidationListener() {
		return e -> {
			generationLabelValue.setText(simulation.getGeneration().toString());
			updateGridRectangles();
		};
	}

	// Simulation active ?
	private InvalidationListener gameRunningListener() {
		return e -> {
			String newLabelString;
			if (simulation.isGameRunning()) {
				newLabelString = "Running";
			} else {
				newLabelString = "Paused";
			}
			gameStateLabelValue.setText(newLabelString);

		};
	}

	// initialisation de l'objet Gridpane
	private void initializeGridpane() {
		int cellsize = this.simulation.getCellsize();
		int gridLenght = simulation.getGrid().getgridLenght();
		int gridWidth = simulation.getGrid().getgridWitdh();

		for (int i = 0; i < gridWidth; i++) {
			gridPane.getColumnConstraints().add(new ColumnConstraints(cellsize));
			gridPane.getRowConstraints().add(new RowConstraints(cellsize));
		}
		for (int i = 0; i < gridLenght; i++) {
			for (int j = 0; j < gridWidth; j++) {
				CellEntity cell = new CellEntity(i, j);
				initializeCells(i, j);
			}
		}
counter++;
	}

	private void initializeCells(int row, int col) {
		int cellSize = simulation.getCellsize();
		Rectangle cellRect = new Rectangle();
		cellRect.setHeight(cellSize);
		cellRect.setWidth(cellSize);
		cellRect.setFill(Color.WHITE);
		cellRect.setStroke(Color.web("#e7ff2e"));
		cellRect.setStrokeType(StrokeType.INSIDE);
		cellRect.setStrokeWidth(0.5);
		cellRect.setSmooth(true);
		// Ajout evènement sur click voir en dessous
		cellRect.setOnMouseClicked(this::mouseClickedEvent);
		gridPane.add(cellRect, col, row);
		if (counter == 0) {
			simulation.setGridNext(simulation.getGrid());
			simulation.getGrid().getGrid()[row][col].setCellState(CellState.EMPTY);
		}
		System.out.println(	 simulation.getGrid().getGrid()[row][col].getCellState().toString());
	};

	private void mouseClickedEvent(MouseEvent e) {
		// Source de l'évènement
		Rectangle eventSource = (Rectangle) e.getSource();
		int eventSourceCol = GridPane.getColumnIndex(eventSource);
		int eventSourceRow = GridPane.getRowIndex(eventSource);

		this.clickType = simulation.getClickType();
		CellEntity cell = new CellEntity(eventSourceRow, eventSourceCol);
		CellState cellState = getCellstatebyInt(this.clickType);
		cell = simulation.getGrid().getGrid()[eventSourceRow][eventSourceCol];
		cell.setCellState(cellState);
		
		simulation.getGrid().getGrid()[eventSourceRow][eventSourceCol].setCellState(cellState);

		Color cellColor = getCellColor(this.clickType);
		eventSource.setFill(cellColor);
	}
	

	// Pour récupérer la couleur de cellule
	public Color getCellColor(int cellState) {
		if (cellState == CellState.EMPTY.getValue()) {
			return Color.web("#FEFEFE");
		} else if (cellState == CellState.BABY.getValue()) {
			return Color.web("#d4ffa6");
		} else if (cellState == CellState.BUSH.getValue()) {
			return Color.web("#8bed45");
		} else if (cellState == CellState.TREE.getValue()) {
			return Color.web("#02a80a");
		} else if (cellState == CellState.FIRE.getValue()) {
			return Color.web("#db6216");
		}
		return Color.web("#FEFEFE");
	}

	public CellState getCellstatebyInt(int cellState) {
		if (cellState == 0) {
			return CellState.EMPTY;
		} else if (cellState == 1) {
			return CellState.BABY;
		} else if (cellState == 2) {
			return CellState.BUSH;
		} else if (cellState == 4) {
			return CellState.TREE;
		} else if (cellState == 5) {
			return CellState.FIRE;
		}
		return null;
	}

	// Mise à jour des cellules

	public void updateGridRectangles() {
		// Mise à jour des nodes
		for (Node child : gridPane.getChildren()) {
			int col = GridPane.getColumnIndex(child);
			int row = GridPane.getRowIndex(child);
			Rectangle cellRect = (Rectangle) child;
			// on récupère l'état de la cellule
			CellState cellstate = simulation.getGridNext().getGrid()[row][col].getCellState();
			System.out.println(cellstate.toString());
			Color cellColor = getCellColor(cellstate.getValue());
			cellRect.setFill(cellColor);

		}
	}

}
