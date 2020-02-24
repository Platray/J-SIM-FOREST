package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class InfoBar extends HBox{

	private static String gridSizeInfo = "Size: %s";
	private static String durationInfo = "Duration: %s";
	
	private Label gridInfo;
	private Label timeInfo;
	private MainView mainView;


	public InfoBar(MainView mainView) {
	
		this.setAlignment(Pos.CENTER);
		this.gridInfo = new Label();
		this.timeInfo =  new Label();
		this.setStyle("-fx-background-color:#99ff7d;-fx-text-fill: #ffffff; -fx-font-size: 18px; -font-family: Arial Black;");
		String sizecontent = Integer.toString(mainView.getGridSize());
		this.gridInfo.setText( String.format(gridSizeInfo, sizecontent));

		String stepInfo = Integer.toString(mainView.getDuration());
		this.timeInfo.setText( String.format(durationInfo, stepInfo));
		
		Pane spacer = new Pane();
		spacer.setMinSize(0, 0);
		spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		this.getChildren().addAll(this.gridInfo,spacer,this.timeInfo);
	}
	

}

