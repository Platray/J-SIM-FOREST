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
		this.setStyle("-fx-background-color:rgb(8,78,4);");

		Pane spacer = new Pane();
		spacer.setMinSize(0, 0);
		spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		this.getChildren().addAll(this.editingTool,spacer,this.pointer);
	}
	
	public void setDrawMode(int drawMode) {
		String modeString ;
		
	if (drawMode == 1) {
		modeString = "Jeune pousse";
	}else if(drawMode == 2){
		modeString = "Buisson";
	}else if(drawMode == 4){
		modeString = "Arbre";
	}else {
		modeString = "Efface";
	}
	this.editingTool.setText( String.format(drawModeFormat, modeString));
	}
	
	public void setCursorPosition(int x , int y ) {
		this.pointer.setText(String.format(cursor, x,y));
	}
	
}

