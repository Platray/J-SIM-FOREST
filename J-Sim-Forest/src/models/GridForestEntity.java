package models;

import enums.CellState;

public class GridForestEntity {
 
	private CellEntity[][] grid;
	private int gridLenght;
	private int gridWidth;

	// On passe la taille voulu au constructeur afin de définir la longueur de X Y

	public GridForestEntity(int gridLenght, int gridWidth) {
		this.gridLenght = gridLenght;
		this.gridWidth = gridWidth;

//		 Création d'une matrice de cellule ayant pour longueur et largeur la gridsize
//		 attention on appel le constructeur par defaut donc les valeur de la cellule
//		 sont à 0
		this.grid = new CellEntity[gridLenght][gridWidth];
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridLenght; j++) {
			grid[i][j]	= new CellEntity(i, j);
			
				
				
			}
		}
	}

	public CellEntity[][] getGrid() {
		return grid;
	}

	

	public int getgridLenght() {
		return gridLenght;
	}

	public int getgridWitdh() {
		return gridWidth;
	}
	 public void setGrid(CellEntity[][] grid) {
	        this.grid = new CellEntity[gridLenght][gridWidth];
	        for (int i = 0; i < gridLenght; i++) {
	            for (int j = 0; j < gridWidth; j++) {
	                this.grid[i][j] = new CellEntity(grid[i][j]);
					grid[i][j].setCellState(CellState.EMPTY);
					

	            }
	        }
	    }

	 

	// Fonction qui réinitialise la grille en mettant les valeurs des cellules à 0
	public void clearGrid() {
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridLenght; j++) {
				grid[i][j].setAge(0);
				grid[i][j].setCellState(CellState.EMPTY);
			}
		}
	}
}
