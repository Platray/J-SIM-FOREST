package models;

import enums.SimSpeed;
import enums.StateRules;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;

public class SimulationEntity {
	
	private GridForestEntity grid;
	private Timeline timeline;
	private ObjectProperty<SimSpeed> gameSpeed; //Vitesse de simulation
    private ObjectProperty<StateRules> ruleSet;//jeu de règle 
    private BooleanProperty gameRunning; //permet de dire si la simulation est en cours 
    private BooleanProperty needsRedraw; //reset ? 
    private LongProperty generation; //informe sur l'avancé de la simulation 
    private int gridSize;
    
    
    
    //Ici il faut mettre les listeners 
    //Les initialize , getneighborsCount, getNeighborsState,play, pause, clear, setNetGeneration
    
    
    
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
	public void setRuleSet(ObjectProperty<StateRules> ruleSet) {
		this.ruleSet = ruleSet;
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

}
