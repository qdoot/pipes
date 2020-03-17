package it.promec.pipes;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class App extends Application
{
	public static void main( String[] args )
    {
    	launch();
    }

	
	@SuppressWarnings("static-access")
	public void start(Stage stage) {
		
		stage.setTitle("Pipes");

		GridPane panel = Panel.create();
		StackPane commandsPanel = CommandsPanel.create();

		HBox root = new HBox();
		root.getChildren().addAll(panel, commandsPanel);
		
		stage.setScene(new Scene(root, Constants.WINDOW_X, Constants.WINDOW_Y));
		stage.setResizable(false);
		stage.show();
		
	}
}
