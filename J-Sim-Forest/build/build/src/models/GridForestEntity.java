package models;

public class GridForestEntity {

	private CellEntity[][] grid;
	private int gridSize;

	// On passe la taille voulu au constructeur afin de d�finir la longueur de X Y

	public GridForestEntity(int gridsize) {
		this.gridSize = gridsize;
//		 Cr�ation d'une matrice de cellule ayant pour longueur et largeur la gridsize
//		 attention on appel le constructeur par defaut donc les valeur de la cellule
//		 sont � 0
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

	// Fonction qui r�initialise la grille en mettant les valeurs des cellules � 0
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
