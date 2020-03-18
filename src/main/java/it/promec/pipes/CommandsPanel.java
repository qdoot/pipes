package it.promec.pipes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CommandsPanel {
	
	public static Color col =  Valve.pipeOnColor;
	public static GridPane buttonsGrid = new GridPane();

	public static StackPane create() {
		
		StackPane commandsPanel = new StackPane();
		
		GridPane background = new GridPane();
		background.setHgap(0);
		background.setVgap(0);
		
		Rectangle rectangle = new Rectangle();
		Color color = Color.LIGHTGRAY;
		rectangle.setFill(color);
		rectangle.setHeight(Constants.RECTANGLE_Y * Constants.ROWS);
		rectangle.setWidth(Constants.RECTANGLE_X * 3);
		background.setStyle("-fx-border-color: black;");
		background.add(rectangle, 0, 0);
		
		Button reset = resetButton();
		Button cambiaColore = cambiaColoreButton();
		
		commandsPanel.getChildren().add(background);
			
		buttonsGrid.setHgap(10);
		buttonsGrid.setVgap(10);
		buttonsGrid.add(reset, 0, 0);
		buttonsGrid.add(cambiaColore, 0, 1);
		Rectangle chosenColor = new Rectangle(25, 25);
		chosenColor.setFill(col);
		buttonsGrid.add(chosenColor, 1, 1);
		buttonsGrid.setPadding(new Insets(10, 10, 10, 10));
		commandsPanel.getChildren().add(buttonsGrid);
		
		return commandsPanel;
	}
	
	private static Button resetButton() {
		
		Button reset = new Button("Reset");
		reset.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	System.out.println("Reset button clicked!");
            	Panel.reset();
            }
        });
		
		return reset;
	}

	private static Button cambiaColoreButton() {
		
		Button cambiaColore = new Button("Cambia colore");
		cambiaColore.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	System.out.println("Cambia colore button clicked!");
            	Rectangle chosenColor = new Rectangle(25, 25);
        		chosenColor.setFill(Valve.pipeOnColor);
            	Valve.changePipeColor();
            	Rectangle chosenColo = (Rectangle) buttonsGrid.getChildren().get(2);
            	chosenColo.setFill(Valve.pipeOnColor);
            }
        });
		
		return cambiaColore;
	}
}
