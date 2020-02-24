package application;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MainView extends VBox {

	private Canvas canvas;

	private Affine affine;
	public static final int EDITING = 0;
	public static final int SIMULATING = 1;

	private Simulation simulation;
	private Simulation initialSimulation;

	private int drawMode = Simulation.BABY;
	private int applicationState = EDITING;
	private Simulator simulator;

	public MainView() {
		this.canvas = new Canvas(400, 400);
		this.canvas.setOnMousePressed(this::handleDraw);
		this.canvas.setOnMouseDragged(this::handleDraw);

		Toolbar toolbar = new Toolbar(this);

		Pane spacer = new Pane();
		spacer.setMinSize(0, 0);
		spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		VBox.setVgrow(spacer, Priority.ALWAYS);

		this.getChildren().addAll(toolbar, this.canvas, spacer);

		this.affine = new Affine();
		this.affine.appendScale(400 / 10f, 400 / 10f);

		this.initialSimulation = new Simulation(10, 10);

		this.simulation = Simulation.copy(this.initialSimulation);
	}

	private void handleDraw(MouseEvent event) {

		if (this.applicationState == SIMULATING) {
			return;
		}
		Point2D simCoord = this.getSimulationCoordinates(event);
		int simX = (int) simCoord.getX();
		int simY = (int) simCoord.getY();

		this.initialSimulation.setState(simX, simY, drawMode);
		draw();
	}

	private Point2D getSimulationCoordinates(MouseEvent event) {
		double mouseX = event.getX();
		double mouseY = event.getY();

		try {
			Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);
			return simCoord;

		} catch (NonInvertibleTransformException e) {
			throw new RuntimeException("NON INVERTIBLE TRANSFORM");
		}

	}

	public void draw() {
		GraphicsContext g = this.canvas.getGraphicsContext2D();
		g.setTransform(this.affine);
		g.setFill(Color.BLUE);
		g.fillRect(0, 0, 450, 450);

		if (this.applicationState == EDITING) {
			drawSimulation(initialSimulation);
		} else {
			drawSimulation(this.simulation);
		}
		g.setStroke(Color.GRAY);
		g.setLineWidth(0.05);

		for (int x = 0; x <= this.simulation.width; x++) {
			g.strokeLine(x, 0, x, 10);
		}
		for (int y = 0; y <= this.simulation.height; y++) {
			g.strokeLine(0, y, 10, y);
		}

	}

	private void drawSimulation(Simulation simulationToDraw) {
		GraphicsContext g = this.canvas.getGraphicsContext2D();

		for (int x = 0; x < simulationToDraw.width; x++) {
			for (int y = 0; y < simulationToDraw.height; y++) {
				if (simulationToDraw.getState(x, y) == 1) {
					g.setFill(Color.web("99ff7d"));
					g.fillRect(x, y, 1, 1);
				} else if (simulationToDraw.getState(x, y) == 2) {
					g.setFill(Color.web("#57e630"));
					g.fillRect(x, y, 1, 1);
				} else if (simulationToDraw.getState(x, y) == 3) {
					g.setFill(Color.web("#57e630"));
					g.fillRect(x, y, 1, 1);
				} else if (simulationToDraw.getState(x, y) == 4) {
					g.setFill(Color.web("#187000"));
					g.fillRect(x, y, 1, 1);
				} else {
					g.setFill(Color.LIGHTGRAY);
					g.fillRect(x, y, 1, 1);
				}
			}
		}
	}

	public void setApplicationState(int applicationState) {
		if (applicationState == this.applicationState) {
			return;
		}

		if (applicationState == SIMULATING) {
			this.simulation = Simulation.copy(this.initialSimulation);
			this.simulator = new Simulator(this, this.simulation);
		}

		this.applicationState = applicationState;

		System.out.println("Application State: " + this.applicationState);
	}

	public Simulation getSimulation() {
		return this.simulation;
	}

	public void setDrawMode(int newDrawMode) {
		this.drawMode = newDrawMode;
	}

	public Simulator getSimulator() {
		return simulator;
	}
}
