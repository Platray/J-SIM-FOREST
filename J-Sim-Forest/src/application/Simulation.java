package application;

public class Simulation {

	public static int DEAD = 0;
	public static int BABY = 1;
	public static int BUSH = 2;
	public static int BUSHOLD = 3;
	public static int TREE = 4;

	int height;
	int width;
	int[][] board;

	public Simulation(int height, int width) {
		this.height = height;
		this.width = width;
		this.board = new int[width][height];
	}
	 public static Simulation copy(Simulation simulation) {
	        Simulation copy = new Simulation(simulation.width, simulation.height);

	        for (int y = 0; y < simulation.height; y++) {
	            for (int x = 0; x < simulation.width; x++) {
	                copy.setState(x, y, simulation.getState(x, y));
	            }
	        }

	        return copy;
	    }
	public void printBoard() {
		System.out.println("---");
		for (int y = 0; y < height; y++) {
			String line = "|";
			for (int x = 0; x < width; x++) {
				if (this.board[x][y] == 0) {
					line += ".";
				} else {
					line += "*";
				}
			}
			line += "|";
			System.out.println(line);
		}
		System.out.println("---\n");
	}

// SET ENTITY ON BOARD  

//set board alive cells young shoot
	public void setAliveShoot(int x, int y) {
		this.board[x][y] = 1;
	}

//set board alive cells shrub
	public void setAliveShrub(int x, int y) {
		this.board[x][y] = 2;
	}

//set board alive cells shrub+1
	public void setAliveShrubold(int x, int y) {
		this.board[x][y] = 3;
	}

//set board alive cells tree
	public void setAliveTree(int x, int y) {
		this.board[x][y] = 4;
	}

//set board dead cells
	public void setDead(int x, int y) {
		this.board[x][y] = 0;
	}

	public void setState(int x, int y, int state) {
		if (x < 0 || x >= width) {
			return;
		}

		if (y < 0 || y >= height) {
			return;
		}

		this.board[x][y] = state;
	}


//récupère tout les voisins
	private int[][] getNeighbors(int x, int y) {
		int[][] neighbors = new int[3][3];

		neighbors[0][0] = getState(x - 1, y - 1);
		neighbors[1][0] = getState(x, y - 1);
		neighbors[2][0] = getState(x + 1, y - 1);
		neighbors[0][1] = getState(x - 1, y);
		neighbors[2][1] = getState(x + 1, y);
		neighbors[0][2] = getState(x - 1, y + 1);
		neighbors[1][2] = getState(x, y + 1);
		neighbors[2][2] = getState(x + 1, y + 1);

		return neighbors;
	}

	private int countStates(int[][] neighbours, int state) {
		int count = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (neighbours[i][j] == state) {
					count++;
				}
			}
		}

		return count;
	}

	public int getState(int x, int y) {
		if (x < 0 || x >= width) {
			return 0;
		}

		if (y < 0 || y >= height) {
			return 0;
		}

		return this.board[x][y];
	}

	public int changeState(int cell) {
		switch (cell) {
		case 0:
			return BABY;

		case 1:
			return BUSH;

		case 2:
			return BUSHOLD;

		case 3:
			return TREE;

		case 4:
			return TREE;

		default:
			return DEAD;
		}
	}

	public void step() {
		int[][] newBoard = new int[width][height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int[][] neighbours = getNeighbors(x, y);
				int countBush = countStates(neighbours, 2);

				int countOldBush = countStates(neighbours, 3);

				int countTree = countStates(neighbours, 4);
				int cell = getState(x, y);
				if (cell == DEAD) {
					if (countTree >= 2 || 
							countBush >= 3 ||
							countTree == 1 && countBush == 2 || 
							countOldBush >= 3 ||
							countTree == 1 && countOldBush == 2 ) {
						newBoard[x][y] = changeState(cell);
					}
					
				} else if (cell == BABY) {
					if (countTree <= 3 || countBush <= 3 ||countOldBush <= 3) {
						newBoard[x][y] = changeState(cell);
					}
				}
				 else if (cell == BUSH || cell == BUSHOLD) {
						newBoard[x][y] = changeState(cell);
					}
				 else if (cell == TREE) {
						newBoard[x][y] = TREE;
					}
				 else {
						changeState(cell);
						
					}
			}
		}
		this.board = newBoard;
	}

}
