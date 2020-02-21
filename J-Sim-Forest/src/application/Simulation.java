package application;

public class Simulation {

	public static int DEAD = 0;
    public static int BABY = 1;
    public static int BUSH = 2;
    public static int BUSHOLD = 3;
    public static int TREE = 4;

	
int height;
int width;
int [][] board;


public Simulation(int height, int width) {
	this.height = height;
	this.width = width;
	this.board = new int[width][height];
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

public int countAliveNeighbours(int x, int y) {
    int count = 0;

    count += getState(x - 1, y - 1);
    count += getState(x, y - 1);
    count += getState(x + 1, y - 1);

    count += getState(x - 1, y);
    count += getState(x + 1, y);

    count += getState(x - 1, y + 1);
    count += getState(x, y + 1);
    count += getState(x + 1, y + 1);

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

public void step() {
    int[][] newBoard = new int[width][height];

    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            int aliveNeighbours = countAliveNeighbours(x, y);

            if (getState(x, y) == 1) {
                if (aliveNeighbours < 2) {
                    newBoard[x][y] = 0;
                } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                    newBoard[x][y] = 1;
                } else if (aliveNeighbours > 3) {
                    newBoard[x][y] = 0;
                }
            } else {
                if (aliveNeighbours == 3) {
                    newBoard[x][y] = 1;
                }
            }

        }
    }

    this.board = newBoard;
}



}
