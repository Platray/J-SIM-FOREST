package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public class MainView extends VBox {

	private Button stepButton;
	private Canvas canvas;

	private Affine affine;

	private Simulation simulation;

	public MainView() {
		this.stepButton = new Button("step");
		this.stepButton.setOnAction(actionEvent -> {
			simulation.step();
			draw();
		});

		this.canvas = new Canvas(400, 400);

		this.getChildren().addAll(this.stepButton, this.canvas);

		this.affine = new Affine();
		this.affine.appendScale(400 / 10f, 400 / 10f);

		this.simulation = new Simulation(10, 10);

		simulation.setAliveShoot(2, 2);
		simulation.setAliveShrub(3, 2);
		simulation.setAliveShrubold(4, 2);

		simulation.setAliveShrubold(6, 5);
		simulation.setAliveTree(6, 6);
	}

	public void draw() {
		GraphicsContext g = this.canvas.getGraphicsContext2D();
		g.setTransform(this.affine);

		g.setFill(Color.LIGHTGRAY);
		g.fillRect(0, 0, 450, 450);

		for (int x = 0; x < this.simulation.width; x++) {
			for (int y = 0; y < this.simulation.height; y++) {
				if (this.simulation.getState(x, y) == 1) {
					g.setFill(Color.web("99ff7d"));
					g.fillRect(x, y, 1, 1);
				} else if (this.simulation.getState(x, y) == 2) {
					g.setFill(Color.web("#57e630"));
					g.fillRect(x, y, 1, 1);
				} else if (this.simulation.getState(x, y) == 3) {
					g.setFill(Color.web("#57e630"));
					g.fillRect(x, y, 1, 1);
				} else if (this.simulation.getState(x, y) == 4) {
					g.setFill(Color.web("#187000"));
					g.fillRect(x, y, 1, 1);
				}else {
					g.setFill(Color.LIGHTGRAY);
					g.fillRect(x, y, 1, 1);
				}
			}
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

}
