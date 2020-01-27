package controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import models.SimulationEntity;

public class PanelController implements Initializable {
	
	private SimulationEntity simulation;

	public PanelController(SimulationEntity simulation) {
		this.simulation = simulation;
	}

	@FXML
	private Button playButton;
	@FXML
	private Button pauseButton;
	@FXML
	private Button clearButton;
	@FXML
	private Slider speedSlider;
	@FXML
	private Button saveButton;
	@FXML
	private Button loadButton;
	@FXML
	private ComboBox<String> rulesCombo;
	@FXML
	private Button randomButton;
	@FXML
	private TextField randomTextField;
	@FXML
	private ComboBox<String> patternsCombo;
	@FXML
	private ImageView patternImageView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Action Listener pour Play Clear Pause
        playButton.setOnAction(e -> playBtnOnAction());
        pauseButton.setOnAction(e -> pauseBtnOnAction());
        clearButton.setOnAction(e -> clearBtnOnAction());
	}
	  private void playBtnOnAction() {
	        System.out.println("Play button was pressed!");
	        simulation.play();
	    }
	  private void pauseBtnOnAction() {
	        System.out.println("Pause button was pressed!");
	        simulation.pause();
	    }
	  private void clearBtnOnAction() {
	        System.out.println("Clear button was pressed!");
	        simulation.clear();
	    }
}
