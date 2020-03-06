package it.promec.pipes;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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

		GridPane background = BackGround.create();

		StackPane root = new StackPane();
		root.getChildren().add(background);
		
		stage.setScene(new Scene(root, Constants.WINDOW_X, Constants.WINDOW_Y));
		stage.setResizable(false);
		stage.show();
		
	}
}
