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
		Button stop = new Button("stop");
		stop.setOnAction(this::handleStop);
		Button start = new Button("start");
		start.setOnAction(this::handleStart);
		Button reset = new Button("reset");
		reset.setOnAction(this::handleReset);
		this.getItems().addAll(drawBABY, drawBush, drawOldBush, drawTree, erase, step, start, stop, reset);
	}

	private void handleStep(ActionEvent actionEvent) {
		System.out.println("Step pressed");
		this.mainView.setApplicationState(MainView.SIMULATING);
		this.mainView.getSimulation().step();
		this.mainView.draw();
	}

	private void handleStart(ActionEvent actionEvent) {
		this.mainView.setApplicationState(MainView.SIMULATING);
		this.mainView.getSimulator().start();
	}

	private void handleStop(ActionEvent actionEvent) {
		this.mainView.getSimulator().stop();
	}

	private void handleReset(ActionEvent actionEvent) {
		this.mainView.setApplicationState(MainView.EDITING);
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
