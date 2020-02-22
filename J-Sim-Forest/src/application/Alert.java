package application;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class Alert extends JSimApplication {
 
    private Label label;
 
    private void showInputTextDialog() {
 
        TextInputDialog dialog = new TextInputDialog("Tran");
        
        //name of the window
        dialog.setTitle("J-Sim-Forest");
        //text to explain what we want on input
        dialog.setHeaderText("Enter grid size:");
        // name of the input asked
        dialog.setContentText("Width :");
 
        Optional<String> result = dialog.showAndWait();
 
        result.ifPresent(witdh -> {
            this.label.setText(witdh);
        });
        
    }
        
    private void showInputTextDialog2() {
	    TextInputDialog dialog2 = new TextInputDialog("test2");
	        
	        //name of the window
	        dialog2.setTitle("J-Sim-Forest");
	        //text to explain what we want on input
	        dialog2.setHeaderText("Enter grid size:");
	        // name of the input asked
	        dialog2.setContentText("Leight :");
	 
	        Optional<String> result2 = dialog2.showAndWait();
	 
	        result2.ifPresent(leight -> {
	            this.label.setText(leight);
	        });
	    }  
    
    private void showInputTextDialog3() {
	    TextInputDialog dialog3 = new TextInputDialog("test2");
	        
	        //name of the window
	        dialog3.setTitle("J-Sim-Forest");
	        //text to explain what we want on input
	        dialog3.setHeaderText("Enter Step number:");
	        // name of the input asked
	        dialog3.setContentText("Step :");
	 
	        Optional<String> result3 = dialog3.showAndWait();
	 
	        result3.ifPresent(step -> {
	            this.label.setText(step);
	        });
	    } 
   
    
 
    @Override
    public void start(Stage stage) {
 
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
 
        this.label = new Label();
        //name of button
        Button button = new Button("Enter your grid size");
 
        button.setOnAction(new EventHandler<ActionEvent>() {
 
        	//print input
            @Override
            public void handle(ActionEvent event) {
                showInputTextDialog();
                showInputTextDialog2();
                showInputTextDialog3();
                stage.hide();
            }
        });
 
        root.getChildren().addAll(button, label);
 
        Scene scene = new Scene(root, 450, 250);
        stage.setTitle("Set Application");
        stage.setScene(scene);
 
        stage.show();
 
    }
 
    public static void main(String args[]) {
        launch(args);
    }
 
}