package controllers;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import java.net.URL;
import java.util.ResourceBundle;

import enums.CellState;
import enums.SimSpeed;
import enums.StateRules;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
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
	private Button previousBtn;
	@FXML
	private Button nextBtn;

	@FXML
	private ImageView patternImageView;
	@FXML
	private CheckBox checkStepByStep;

	@FXML
	private CheckBox babyClick;
	@FXML
	private CheckBox bushClick;
	@FXML
	private CheckBox treeClick;
	@FXML
	private CheckBox fireClick;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Action Listener pour Play Clear Pause
		playButton.setOnAction(e -> playBtnOnAction());
		pauseButton.setOnAction(e -> pauseBtnOnAction());
		clearButton.setOnAction(e -> clearBtnOnAction());
		//listener pour activer mode Pas à pas 
		checkStepByStep.setOnAction(e -> activateStepByStep());

		babyClick.setOnAction(setAddCellValue);
		bushClick.setOnAction(setAddCellValue);
		treeClick.setOnAction(setAddCellValue);
		fireClick.setOnAction(setAddCellValue);

		rulesCombo.getItems().setAll(StateRules.getRuleSetLabels());
		rulesCombo.getSelectionModel().selectFirst();
		rulesCombo.valueProperty().addListener(getRulesComboChangeListener());
	}

	EventHandler<ActionEvent> setAddCellValue = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {

			if (event.getSource() instanceof CheckBox) {
				checkBoxUncheck(event);

			}
		}
	};

	private void checkBoxUncheck(ActionEvent event) {
		if (event.getSource() instanceof CheckBox) {
			CheckBox chk = (CheckBox) event.getSource();
			if ("Baby".equals(chk.getText())) {
				simulation.setClickType(1);
				bushClick.setSelected(false);
				treeClick.setSelected(false);
				fireClick.setSelected(false);

			} else if ("bush".equals(chk.getText())) {
				simulation.setClickType(2);

				babyClick.setSelected(false);
				treeClick.setSelected(false);
				fireClick.setSelected(false);

			} else if ("tree".equals(chk.getText())) {
				simulation.setClickType(4);
				bushClick.setSelected(false);
				babyClick.setSelected(false);
				fireClick.setSelected(false);

			} else if ("fire".equals(chk.getText())) {
				bushClick.setSelected(false);
				babyClick.setSelected(false);
				treeClick.setSelected(false);
				simulation.setClickType(5);

			}
		}
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

	private void activateStepByStep() {
		if (checkStepByStep.isSelected()) {
			System.out.println("Step by step mode Activated");
		} else {
			System.out.println("Step by step mode Activated");
		}
	}

	private ChangeListener<String> getRulesComboChangeListener() {
		return (observable, oldValue, newValue) -> {
			System.out.println("The rules combo value was changed!");
			System.out.println("The new value is " + rulesCombo.getValue());
			simulation.setRuleSet(StateRules.fromString(rulesCombo.getValue()));
		};
	}

	private ChangeListener<Number> getSliderChangeListener() {
		return (observable, oldValue, newValue) -> {
			// This bit keeps to property listener from firing unless the slider in on a
			// tick mark.
			// The slider value is a double, but we are only interested in the whole numbers
			// at ticks.
			speedSlider.setValue(newValue.intValue());
			if (newValue.intValue() != oldValue.intValue()) {
				speedSliderChangeAction();
			}
		};
	}

	private void speedSliderChangeAction() {
		System.out.println("The speed slider value was changed!");
		System.out.println("The new value is " + speedSlider.getValue());
		int speedSliderValue = (int) speedSlider.getValue();
		if (speedSliderValue == 0) {
			simulation.initializeTimeline(SimSpeed.VERYSLOW);
		} else if (speedSliderValue == 1) {
			simulation.initializeTimeline(SimSpeed.SLOW);
		} else if (speedSliderValue == 2) {
			simulation.initializeTimeline(SimSpeed.MEDIUM);
		} else if (speedSliderValue == 3) {
			simulation.initializeTimeline(SimSpeed.FAST);
		} else if (speedSliderValue == 4) {
			simulation.initializeTimeline(SimSpeed.VERYFAST);
		}
	}

	private void initializeSpeedSlider() {
		// SpeedSlider options
		speedSlider.setMin(0);
		speedSlider.setMax(4);
		speedSlider.setValue(0);
		speedSlider.setShowTickLabels(true);
		speedSlider.setShowTickMarks(true);
		speedSlider.setMajorTickUnit(1);
		speedSlider.setMinorTickCount(0);
		speedSlider.setBlockIncrement(1);
		speedSlider.setSnapToTicks(true);
		speedSlider.setLabelFormatter(new StringConverter<Double>() {
			@Override
			public String toString(Double n) {
				if (n == 0)
					return SimSpeed.VERYSLOW.getLabel();
				if (n == 1)
					return SimSpeed.SLOW.getLabel();
				if (n == 2)
					return SimSpeed.MEDIUM.getLabel();
				if (n == 3)
					return SimSpeed.FAST.getLabel();
				if (n == 4)
					return SimSpeed.VERYFAST.getLabel();
				return SimSpeed.SLOW.toString();
			}

			@Override
			public Double fromString(String s) {
				if (s.equals(SimSpeed.VERYSLOW.getLabel()))
					return 0d;
				if (s.equals(SimSpeed.SLOW.getLabel()))
					return 1d;
				if (s.equals(SimSpeed.MEDIUM.getLabel()))
					return 2d;
				if (s.equals(SimSpeed.FAST.getLabel()))
					return 3d;
				if (s.equals(SimSpeed.VERYFAST.getLabel()))
					return 4d;
				return 0d;
			}
		});
	}
}
