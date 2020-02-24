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
	
	private InfoBar infobar;
	private Canvas canvas;
	private Affine affine;
	public static final int EDITING = 0;
	public static final int SIMULATING = 1;
	private Simulation simulation;
	private Simulation initialSimulation;
	private int drawMode = Simulation.BABY;
	private int applicationState = EDITING;
	private Simulator simulator;
	private int duration;
	private int gridSize;

	public MainView(int gridSize , int duration) {
		
		this.canvas = new Canvas(400, 400);
		this.canvas.setOnMousePressed(this::handleDraw);
		this.canvas.setOnMouseDragged(this::handleDraw);
		this.duration = duration;
		Toolbar toolbar = new Toolbar(this);

		Pane spacer = new Pane();
		spacer.setMinSize(0, 0);
		spacer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		this.infobar = new InfoBar();
		this.infobar.setDrawMode(this.drawMode);
		this.infobar.setCursorPosition(0, 0);
		spacer.setStyle("-fx-background-color: #99ff7d;");
		this.setStyle("-fx-background-color: #99ff7d;");

		VBox.setVgrow(spacer, Priority.ALWAYS);
		this.getChildren().addAll(toolbar, this.canvas, spacer,infobar);
		this.affine = new Affine();
		this.affine.appendScale(400 / gridSize, 400 / gridSize);
		this.initialSimulation = new Simulation(100, 100);
		
		this.simulation = Simulation.copy(this.initialSimulation);
		
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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
		g.fillRect(0, 0, 450, 450);

		if (this.applicationState == EDITING) {
			drawSimulation(initialSimulation);
		} else {
			drawSimulation(this.simulation);
		}
		g.setStroke(Color.BLACK);
		g.setLineWidth(0.05);

		for (int x = 0; x <= this.simulation.width; x++) {
			g.strokeLine(x, 0, x, gridSize);
		}
		for (int y = 0; y <= this.simulation.height; y++) {
			g.strokeLine(0, y, gridSize, y);
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

	public int getGridSize() {
		return gridSize;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
}
