package models;

import enums.CellState;
import enums.SimSpeed;
import enums.StateRules;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;
import models.GridForestEntity;

public class SimulationEntity {
	private GridForestEntity grid;
	private GridForestEntity gridNext;
	private GridForestEntity neighbours;
	private Timeline timeline;
	private ObjectProperty<SimSpeed> gameSpeed; // Vitesse de simulation
	private ObjectProperty<StateRules> ruleSet;// jeu de règle
	private BooleanProperty gameRunning; // permet de dire si la simulation est en cours
	private BooleanProperty needsRedraw; // reset ?
	private LongProperty generation; // informe sur l'avancé de la simulation
	private IntegerProperty gridLenght;
	private IntegerProperty gridWidth;
	private int cellsize;
	private int clickType;

	// Ici il faut mettre les listeners
	// Les initialize , getneighborsCount, getNeighborsState,play, pause, clear,
	// setNetGeneration

	public SimulationEntity(int gridlenght, int gridWidth, int cellsize) {

		this.grid = new GridForestEntity(gridWidth, gridlenght);

		this.cellsize = cellsize;
		this.gridNext = new GridForestEntity(gridWidth, gridlenght);
		// properties.
		this.gameRunning = new SimpleBooleanProperty();
		this.gameRunning.set(false);
		this.needsRedraw = new SimpleBooleanProperty();
		this.needsRedraw.set(false);
		
		//this.ruleSet.set(StateRules.FOREST);;
		this.gameSpeed = new SimpleObjectProperty<>();
		this.gameSpeed.set(SimSpeed.VERYSLOW);
		this.generation = new SimpleLongProperty();
		this.generation.set(0);
		initializeTimeline(SimSpeed.VERYSLOW);

	}

	public void initializeTimeline(SimSpeed speed) {
		// Définition de la vitesse d'éxecution de la simulation
		this.gameSpeed.set(speed);
		// Arrêt de la Timeline précedent si nécessaire
		if (timeline != null) {
			timeline.stop();
		}
		// Définition de la Timeline avec la longueur d'intervale voulu
		KeyFrame keyFrame = new KeyFrame(Duration.millis(speed.toValue()), e -> {
			nextGeneration();
			generation.set(generation.get() + 1);
			setNeedsRedraw(true);
		});

		// Attacher la keyframe à la timeline
		timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Timeline.INDEFINITE);

		// Si la simulation est déja en cours on la relance
		if (gameRunning.get()) {
			timeline.play();
		}
	}

	private void nextGeneration() {
		// LOGIQUE DE CHANGEMENT DETAT des cellules
		for (int col = 0; col < grid.getgridLenght(); col++) {
			for (int row = 0; row < grid.getgridWitdh(); row++) {
				CellEntity cell = grid.getGrid()[row][col];
				grid.getGrid()[row][col] = cell;
				forestGrowth(grid, row, col);

			}

		}
		
	}

	public void play() {
		gameRunning.set(true);
		timeline.play();
	}

	public void pause() {
		gameRunning.set(false);
		timeline.pause();
	}

	public void clear() {
		gameRunning.set(false);
		timeline.stop();
		grid.clearGrid();
		gridNext.clearGrid();
		setGeneration(0);

	}

	private void setGeneration(int i) {
generation.setValue(i);
	}

	public GridForestEntity getGridNext() {
		return gridNext;
	}

	public void setGridNext(GridForestEntity gridNext) {
		this.gridNext = gridNext;
	}

	public int getCellsize() {
		return cellsize;
	}

	// récupère tout les voisins
	private GridForestEntity getNeighbors(int row, int col, int gridWidth, int gridHeight) {
		GridForestEntity neighbors = new GridForestEntity(3, 3);

		neighbors.getGrid()[0][0] = this.getNeighbor(row - 1, col - 1, gridWidth, gridHeight);
		neighbors.getGrid()[1][0] = this.getNeighbor(row - 1, col, gridWidth, gridHeight);
		neighbors.getGrid()[2][0] = this.getNeighbor(row - 1, col + 1, gridWidth, gridHeight);
		neighbors.getGrid()[0][1] = this.getNeighbor(row, col - 1, gridWidth, gridHeight);
		neighbors.getGrid()[1][1] = this.getNeighbor(row, col, gridWidth, gridHeight);
		neighbors.getGrid()[2][1] = this.getNeighbor(row, col + 1, gridWidth, gridHeight);
		neighbors.getGrid()[0][2] = this.getNeighbor(row + 1, col - 1, gridWidth, gridHeight);
		neighbors.getGrid()[1][2] = this.getNeighbor(row + 1, col, gridWidth, gridHeight);
		neighbors.getGrid()[2][2] = this.getNeighbor(row + 1, col + 1, gridWidth, gridHeight);
		
		return neighbors;
	}

	// récupère un voisin
	private CellEntity getNeighbor(int row, int col, int gridWidth, int gridHeight) {
		CellEntity cell = new CellEntity(row,col);

		if (row < 0 || row >= gridHeight || col < 0 || col >= gridWidth) {
			cell = new CellEntity(row, col);
		} else {
			cell = grid.getGrid()[row][col];
		}

		return cell;
	}

	// compte tout les voisins d'un même état
	private int countStates(GridForestEntity neighbours, CellState state) {
		int count = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (neighbours.getGrid()[i][j].getCellState() == state) {
					count++;
				}
			}
		}

		return count;
	}

	public void changeState(CellEntity cell) {
		switch (cell.getCellState()) {
		case EMPTY:
			cell.setCellState(CellState.BABY);
			break;
		case BABY:
			cell.setCellState(CellState.BUSH);
			break;
		case BUSH:
			if (cell.getAge() == 0) {
				cell.setCellState(CellState.BUSH);
				cell.setAge(1);
			} else if (cell.getAge() == 1) {
				cell.setCellState(CellState.TREE);
				cell.setAge(0);
			}
			break;
		case TREE:
			cell.setCellState(CellState.TREE);
			break;
		case FIRE:
			cell.setCellState(CellState.ASHES);
			break;
		case ASHES:
			cell.setCellState(CellState.EMPTY);
			break;

		default:
			cell.setCellState(CellState.EMPTY);
		}

	}
	// Modèle croissance

	private void forestGrowth(GridForestEntity previousGrid, int row, int col) {
				System.out.println("DANS FOREST GROWTH");
				CellEntity cell = new CellEntity(previousGrid.getGrid()[row][col]);

				GridForestEntity neighbors = getNeighbors( row,  col,  previousGrid.getgridWitdh(),previousGrid.getgridLenght());
				cell.setNeighbors(neighbors);
		CellEntity newCell = new CellEntity(gridNext.getGrid()[row][col]);
		newCell.setCellState(cell.getCellState());
		if (cell.getCellState() == CellState.EMPTY) {
			if (((countStates(cell.getNeighbors(), CellState.TREE) >= 2 || countStates(cell.getNeighbors(), CellState.BUSH) >= 3
					|| (this.countStates(cell.getNeighbors(), CellState.TREE) == 1
							&& this.countStates(cell.getNeighbors(), CellState.BUSH) == 2)))) {

				changeState(newCell);
				cell = newCell;

				System.out.println("1 GROWTH");

			}
		} else if (cell.getCellState() == CellState.BABY) {
			if ((countStates(cell.getNeighbors(), CellState.TREE) <= 3) || (countStates(cell.getNeighbors(), CellState.BUSH)) <= 3) {
				changeState(newCell);
				System.out.println("3 GROWTH");

			}
		} else if (cell.getCellState() == CellState.BUSH) {
			if (cell.getAge() == 0) {
				newCell.setAge(1);
				changeState(newCell);
				cell = newCell;
			} else {
				changeState(newCell);
				cell = newCell;

				
			}
			System.out.println("2 GROWTH");

		}
	}

	public void getCellState(int i, int j) {

	}

	public void toggleCellState(int i, int j, boolean current) {

	}

	public void setCellsize(int cellsize) {
		this.cellsize = cellsize;
	}

//La grille
	public GridForestEntity getGrid() {
		return grid;
	}

	public void setGrid(GridForestEntity grid) {
		this.grid = grid;
	}

//Timeline
	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

//VItesse dexecution
	public ObjectProperty<SimSpeed> getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(ObjectProperty<SimSpeed> gameSpeed) {
		this.gameSpeed = gameSpeed;
	}

//modèle sélectionné 
	public ObjectProperty<StateRules> getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(StateRules ruleSet) {
		this.ruleSet.set(ruleSet);
	}

//vérifier état de la simulation
	public BooleanProperty gameRunningProperty() {
		return gameRunning;
	}

	public void setGameRunning(BooleanProperty gameRunning) {
		this.gameRunning = gameRunning;
	}

	public boolean isGameRunning() {
		return gameRunning.get();
	}
//pour trigger le refresh

	public BooleanProperty needsRedrawProperty() {
		return needsRedraw;
	}

	public void setNeedsRedraw(Boolean needsRedraw) {
		this.needsRedraw.setValue(needsRedraw);
	}

//pour gérer l'age du bush
	public LongProperty getGeneration() {
		return generation;
	}

	public void setGeneration(LongProperty generation) {
		this.generation = generation;
	}

	public final LongProperty generationProperty() {
		return generation;
	}

//largeur
	public IntegerProperty gridWidth(IntegerProperty gridWidth) {
		return gridWidth;
	}

	public int getWidth() {
		return gridWidth.get();
	}

	public void setgridWidth(int gridWidth) {
		this.gridWidth.setValue(gridWidth);
	}

//hauteur
	public void setgridLenght(IntegerProperty gridLenght) {
		this.gridLenght = gridLenght;
	}

	public int getgridLenght() {
		return gridWidth.get();
	}

//Mode de clic pour ajout cellule
	public int getClickType() {
		return this.clickType;
	}

	public void setClickType(int value) {
		this.clickType = value;
	}
}
