package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import enums.CellState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import models.SimulationEntity;

public class GridForestController implements Initializable {
	private int clickType;

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

	public GridForestController(SimulationEntity simulation) {
		this.simulation = simulation;
	}

	@Override
	public void initialize(URL location, ResourceBundle ressources) {
		// grille
		initializeGridpane();

		// Trigger le raffraichissement
		simulation.needsRedrawProperty().addListener(needsRefreshChangeListener());
	}
	// LES LISTENERS pour être à l'écoute de la forêt:

	private ChangeListener<Boolean> needsRefreshChangeListener() {
		return new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				simulation.needsRedrawProperty().removeListener(this);
				updateGridRectangles();
				simulation.setNeedsRedraw(false);
				simulation.needsRedrawProperty().addListener(this);

				System.out.println("MISE A JOUR ");
			}

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

		};

	}

	// initialisation de l'objet Gridpane
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
		cellRect.setStroke(Color.web("#e7ff2e"));
		cellRect.setStrokeType(StrokeType.INSIDE);
		cellRect.setStrokeWidth(0.5);
		cellRect.setSmooth(true);
		// Ajout evènement sur click voir en dessous
		cellRect.setOnMouseClicked(this::mouseClickedEvent);
		gridPane.add(cellRect, col, row);

	};

	private void mouseClickedEvent(MouseEvent e) {
		// Source de l'évènement
		Rectangle eventSource = (Rectangle) e.getSource();
		int eventSourceCol = GridPane.getColumnIndex(eventSource);
		int eventSourceRow = GridPane.getRowIndex(eventSource);

		int state = simulation.getClickType();
		Color cellColor = getCellColor(state);
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
		return null;
	}

	// Mise à jour des cellules
	private void updateGridRectangles() {
		// Mise à jour des nodes
		for (Node child : gridPane.getChildren()) {
			int col = GridPane.getColumnIndex(child);
			int row = GridPane.getRowIndex(child);
			Rectangle cellRect = (Rectangle) child;
			// on récupère l'état de la cellule
			CellState cell = simulation.getGrid().getGrid()[row][col].getCellState();
			Color cellColor = getCellColor(cell.getValue());
			cellRect.setFill(cellColor);
		}
	}

}
