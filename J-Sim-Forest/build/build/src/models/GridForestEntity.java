package models;

public class GridForestEntity {

	private CellEntity[][] grid;
	private int gridSize;

	// On passe la taille voulu au constructeur afin de définir la longueur de X Y

	public GridForestEntity(int gridsize) {
		this.gridSize = gridsize;
//		 Création d'une matrice de cellule ayant pour longueur et largeur la gridsize
//		 attention on appel le constructeur par defaut donc les valeur de la cellule
//		 sont à 0
		this.grid = new CellEntity[gridsize][gridsize];
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = new CellEntity();
			}
		}
	}

	public CellEntity[][] getGrid() {
		return grid;
	}

	// Fonction qui réinitialise la grille en mettant les valeurs des cellules à 0
	public void clearGrid() {
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j].setAge(0);
				;
				grid[i][j].setState(0);
			}
		}
	}
}
