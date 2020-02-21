package application;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class Toolbar extends ToolBar {

	private MainView mainView;

	public Toolbar(MainView mainView) {
		
		this.mainView = mainView;
		Button drawBABY = new Button("Add baby");
		drawBABY.setOnAction(this::drawBaby);
		Button drawBush = new Button("Add bush");
		drawBush.setOnAction(this::drawBush);
		Button drawOldBush = new Button("Add Oldbush");
		drawOldBush.setOnAction(this::drawOldBush);
		Button drawTree = new Button("Add Tree");
		drawTree.setOnAction(this::drawTree);
		Button erase = new Button("Empty ");
		erase.setOnAction(this::erase);
		Button step = new Button("Step");
		step.setOnAction(this::handleStep);

		this.getItems().addAll(drawBABY, drawBush,drawOldBush,drawTree,erase, step);
	}

	private void handleStep(ActionEvent actionEvent) {
		System.out.println("Step pressed");
		this.mainView.getSimulation().step();
		this.mainView.draw();
	}

	private void drawBaby(ActionEvent actionEvent) {
		System.out.println("drawBABY");
		this.mainView.setDrawMode(Simulation.BABY);
	}

	private void drawBush(ActionEvent actionEvent) {
		System.out.println("Draw bush");
		this.mainView.setDrawMode(Simulation.BUSH);
	}
	private void drawOldBush(ActionEvent actionEvent) {
		System.out.println("draw old Bush");
		this.mainView.setDrawMode(Simulation.BUSHOLD);
	}
	private void drawTree(ActionEvent actionEvent) {
		System.out.println("draw Tree");
		this.mainView.setDrawMode(Simulation.TREE);
	}
	private void erase(ActionEvent actionEvent) {
		System.out.println("draw DEAD");
		this.mainView.setDrawMode(Simulation.DEAD);
	}
}
