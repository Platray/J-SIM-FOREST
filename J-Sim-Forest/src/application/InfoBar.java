package application;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox{

	private static String drawModeFormat = "Draw Mode: %s";
	private static String cursor = "Cursor:(%d, %d)";
	
	private Label pointer;
	private Label editingTool;
	public InfoBar() {
		this.pointer = new Label();
		this.editingTool =  new Label();
		
		Pane spacer = new Pane();
		spacer.setMinSize(0, 0);
		spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		this.getChildren().addAll(this.editingTool,spacer,this.pointer);
	}
	
	public void setDrawMode(int drawMode) {
		String modeString ;
		
	if (drawMode == Simulation.BABY) {
		modeString = "Jeune pousse";
	}else if(drawMode == Simulation.BUSH){
		modeString = "Buisson";
	}else if(drawMode == Simulation.TREE){
		modeString = "Arbre";
	}else {
		modeString = "Efface";
	}
	this.editingTool.setText(String.format(drawModeFormat, modeString));
	}
	
	public void setCursorPosition(int x , int y ) {
		this.pointer.setText(String.format(cursor, x,y));
	}
	
}

