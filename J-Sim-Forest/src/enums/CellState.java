package enums;

public enum CellState {
	  EMPTY(0), BABY(1), BUSH(2), BUSH2(3), TREE(4), FIRE(5), ASHES(6), INSECT(7);

    private int value;

    CellState(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }
    
   
}
