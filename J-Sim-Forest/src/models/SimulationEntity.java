package models;

import enums.SimSpeed;
import enums.StateRules;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
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
	private Timeline timeline;
	private ObjectProperty<SimSpeed> gameSpeed; // Vitesse de simulation
	private ObjectProperty<StateRules> ruleSet;// jeu de règle
	private BooleanProperty gameRunning; // permet de dire si la simulation est en cours
	private BooleanProperty needsRedraw; // reset ?
	private LongProperty generation; // informe sur l'avancé de la simulation
	private int gridSize;
	private int cellsize;
	private int clickType;

	// Ici il faut mettre les listeners
	// Les initialize , getneighborsCount, getNeighborsState,play, pause, clear,
	// setNetGeneration

	public SimulationEntity(int gridsize, int cellsize) {
		this.gridSize = gridsize;
		this.cellsize = cellsize;
		this.grid = new GridForestEntity(gridsize);
		this.gridNext = new GridForestEntity(gridsize);
		// properties.
		this.gameRunning = new SimpleBooleanProperty();
		this.gameRunning.set(false);
		this.needsRedraw = new SimpleBooleanProperty();
		this.needsRedraw.set(false);
		// this.ruleSet.set(StateRules.FOREST);
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
			getGeneration();
			generation.set(generation.get() + 1);
		});
		// Attacher la keyframe à la timeline
		timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Timeline.INDEFINITE);

		// Si la simulation est déja en cours on la relance
		if (gameRunning.get()) {
			timeline.play();
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
	        setGeneration(generation);;
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

	
	public void getCellState(int i, int j) {
		
	}

	
	public void toggleCellState(int i, int j, boolean current) {
		
	}

	public void setCellsize(int cellsize) {
		this.cellsize = cellsize;
	}

	public GridForestEntity getGrid() {
		return grid;
	}

	public void setGrid(GridForestEntity grid) {
		this.grid = grid;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public ObjectProperty<SimSpeed> getGameSpeed() {
		return gameSpeed;
	}

	public void setGameSpeed(ObjectProperty<SimSpeed> gameSpeed) {
		this.gameSpeed = gameSpeed;
	}

	public ObjectProperty<StateRules> getRuleSet() {
		return ruleSet;
	}

	public void setRuleSet(StateRules ruleSet) {
		this.ruleSet.set(ruleSet);
	}

	public BooleanProperty getGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(BooleanProperty gameRunning) {
		this.gameRunning = gameRunning;
	}

	public BooleanProperty getNeedsRedraw() {
		return needsRedraw;
	}

	public void setNeedsRedraw(BooleanProperty needsRedraw) {
		this.needsRedraw = needsRedraw;
	}

	public LongProperty getGeneration() {
		return generation;
	}

	public void setGeneration(LongProperty generation) {
		this.generation = generation;
	}

	public int getGridSize() {
		return gridSize;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	 public int getClickType() {
		    return clickType;
		  }

		  public void setClickType(int value) {
		    this.clickType = value;
		  }
}
