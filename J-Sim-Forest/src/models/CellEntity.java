package models;


import enums.CellState;
import enums.StateRules;

public class CellEntity {
	private GridForestEntity neighbors;

	private long age;
	private StateRules stateRule;
	private CellState cellstate;
	private int positionY;
	private int positionX;

	
	public CellEntity(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	
	
//constructeur de copie on lui passe tune cellule en paramètre
	public CellEntity(CellEntity toCopy) {
		this.positionX = toCopy.positionX;
		this.positionY = toCopy.positionY;
		 this.cellstate = toCopy.cellstate ;
		 this.stateRule = toCopy.stateRule ;
		 this.neighbors = toCopy.neighbors ;



	}

public CellState getCellState() {
	return cellstate;
}

public void setCellState(CellState cellState) {
	this.cellstate = cellState;
}
public CellState getCellStateFromInt(int intState) {
	if (intState == 0) {
		return   CellState.EMPTY;
	}else if (intState == 1) {
		return   CellState.BABY;
	}else if (intState == 2) {
		return   CellState.BUSH;
	}else if (intState == 0) {
		return   CellState.TREE;
	}else if (intState == 0) {
		return   CellState.FIRE;
	}
	return CellState.EMPTY;
	
}
	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}


	public void incrementAge() {
		age = age + 1;
	}



	public GridForestEntity getNeighbors() {
		return neighbors;
	}



	public void setNeighbors(GridForestEntity neighbors) {
		this.neighbors = neighbors;
	}



	public int getPositionY() {
		return positionY;
	}



	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}



	public int getPositionX() {
		return positionX;
	}



	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

}
