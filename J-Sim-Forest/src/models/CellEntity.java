package models;

public class CellEntity {
	private int state;
	private long age;

	public CellEntity(int state, long age) {
		this.state = state;
		this.age = age;
	}

	//constructeur de copie on lui passe une cellule en paramètre
		public CellEntity() {
			this.state = 0;
			this.age = 0;
		}
	
//constructeur de copie on lui passe une cellule en paramètre
	public CellEntity(CellEntity toCopy) {
		this.state = toCopy.state;
		this.age = toCopy.age;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

}
