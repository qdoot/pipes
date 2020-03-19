package it.promec.pipes;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Panel {

	private static List<Valve> valves = new ArrayList<Valve>();
	private static List<Pipe> pipes = new ArrayList<Pipe>();
	
	public static GridPane create() {
		
		GridPane grid = new GridPane();
		grid.setHgap(0);
		grid.setVgap(0);
		
		addBackGround(grid);		
		addValvesAndPipes(grid);
		
		return grid;
	}

	private static void addBackGround(GridPane grid) {
		boolean isWhite = true;
		
		for(int i=0; i<Constants.ROWS; i++) {
			for(int j=0; j<Constants.COLUMNS; j++) {
				
				Rectangle rectangle = new Rectangle();
				
				Color color;				
				//color = (isWhite) ? Color.rgb(100, 100, 100) : Color.rgb(130, 130, 130);
				color = Constants.BACKGROUND_COLOR;
				
				isWhite = !isWhite;

				rectangle.setFill(color);
				rectangle.setHeight(Constants.RECTANGLE_Y);
				rectangle.setWidth(Constants.RECTANGLE_X);
				grid.add(rectangle, j, i);
			}
		}
	}

	private static void addValvesAndPipes(GridPane grid) {
		
		for(int i=1; i<Constants.ROWS - 1; i++) {
			for(int j=1; j<Constants.COLUMNS - 1; j++) {
				
				
				ColumnConstraints col = new ColumnConstraints();
				col.setHalignment(HPos.CENTER);
				grid.getColumnConstraints().add(col);
				RowConstraints row = new RowConstraints ();
				row.setValignment(VPos.CENTER);
				grid.getRowConstraints().add(row);
				
				if((i%2==1)&&(j%2==1)) {
					if(!isUnwantedElement(j,i)) {
						Valve valve = new Valve(j, i, valves, pipes);
						valves.add(valve);
						grid.add(valve.getBackground(), j, i);
						grid.add(valve.getGraphic(), j, i);
					}
				}
						
				if((i%2==1)&&(j%2==0)) {
					if(!isUnwantedElement(j,i)) {
						Pipe pipe = new Pipe(j, i, true);
						pipes.add(pipe);
						grid.add(pipe.getGraphic(), j, i);
					}
				}
				
				if((i%2==0)&&(j%2==1)) {
					if(!isUnwantedElement(j,i)) {
						Pipe pipe = new Pipe(j, i, false);
						pipes.add(pipe);
						grid.add(pipe.getGraphic(), j, i);
					}
				}
				
				addSpecialElements(grid);
				
			}
		}
	}
	
	private static void addSpecialElements(GridPane grid) {
		
		//START lungo collegamento ultimo input a destra
		for(int x=10; x<=13; x++) {
			Pipe horizontalPipe = new Pipe(x, 7, true);
			pipes.add(horizontalPipe);
			grid.add(horizontalPipe.getGraphic(), x, 7);
		}
		//END lungo collegamento ultimo input a destra
		
		//START input e collegamento sulla sinistra
		Pipe horizontalPipe = new Pipe(3, 7, true);
		pipes.add(horizontalPipe);
		grid.add(horizontalPipe.getGraphic(), 3, 7);
		
		/*
		Pipe horizontalPipe2 = new Pipe(4, 7, true);
		pipes.add(horizontalPipe2);
		grid.add(horizontalPipe.getGraphic(), 4, 7);*/
		
		Valve valve = new Valve(2, 7, valves, pipes);
		valves.add(valve);
		grid.add(valve.getBackground(), 2, 7);
		grid.add(valve.getGraphic(), 2, 7);
		//END input e collegamento sulla sinistra
		
		//START uscite
		Pipe uscita1 = new Pipe(5, 8, false);
		Pipe uscita2 = new Pipe(7, 8, false);
		Pipe uscita3 = new Pipe(9, 8, false);
		Pipe uscita4 = new Pipe(11, 6, false);
		Pipe uscita5 = new Pipe(13, 6, false);
		Pipe uscita6 = new Pipe(3, 6, false);
		
		pipes.add(uscita1);
		pipes.add(uscita2);
		pipes.add(uscita3);
		pipes.add(uscita4);
		pipes.add(uscita5);
		pipes.add(uscita6);
		
		grid.add(uscita1.getGraphic(), 5, 8);
		grid.add(uscita2.getGraphic(), 7, 8);
		grid.add(uscita3.getGraphic(), 9, 8);
		grid.add(uscita4.getGraphic(), 11, 6);
		grid.add(uscita5.getGraphic(), 13, 6);
		grid.add(uscita6.getGraphic(), 3, 6);
		//END uscite
	}

	public static boolean isUnwantedElement(int x, int y) {
		
		boolean isUnwanted = false;
		
		List<int[]> unwantedElementCoordinates = new ArrayList<int[]>();
		unwantedElementCoordinates.add(new int[] {3,7});
		unwantedElementCoordinates.add(new int[] {3,6});
		
		unwantedElementCoordinates.add(new int[] {10,7});
		unwantedElementCoordinates.add(new int[] {11,7});
		unwantedElementCoordinates.add(new int[] {11,6});
		unwantedElementCoordinates.add(new int[] {12,7});
		unwantedElementCoordinates.add(new int[] {13,7});
		unwantedElementCoordinates.add(new int[] {13,6});
		
		List<Integer> unwantedRows = new ArrayList<Integer>();
		List<Integer> unwantedColumns = new ArrayList<Integer>();
		unwantedRows.add(8);
		unwantedRows.add(9);
		unwantedColumns.add(1);
		unwantedColumns.add(2);
		
		
		for(int[] unwantedElementCoordinate : unwantedElementCoordinates) {
			if((unwantedElementCoordinate[0] == x) && (unwantedElementCoordinate[1] == y)) {
				isUnwanted = true;
				break;
			}
			
			for(int row: unwantedRows)
				if(y == row)
					isUnwanted = true;
			
			for(int column: unwantedColumns)
				if(x == column)
					isUnwanted = true;
		}
		
		return isUnwanted;
	}

	public static void reset() {
		
		for(Pipe pipe: pipes) {
			pipe.getGraphic().setFill(Constants.PIPE_OFF_COLOR);
		}
		
		hidePipesBetweenInputs(pipes);
		
		for(Valve valve: valves) {
			valve.getGraphic().setFill(Constants.VALVE_OFF_COLOR);
			valve.setOpen(false);
			if(!valve.isInput()) {
				valve.getGraphic().setStyle("-fx-cursor: none;"); //Hand pointer on mouse over
				valve.setClickable(false);
			} else {
				valve.getGraphic().setStyle("-fx-cursor: hand;"); //Hand pointer on mouse over
				valve.setClickable(true);
			}
		}
	}
	
	public static void undo() {
		
		for(Valve valve: valves) {
			if((valve.getX() == Valve.getxLastClicked())&&(valve.getY() == Valve.getyLastClicked())) {	
				valve.getGraphic().setFill(Constants.VALVE_OFF_COLOR);
				valve.setOpen(false);
				valve.setClickable(true);
				valve.getGraphic().setStyle("-fx-cursor: hand;"); //Hand pointer on mouse over
			}
		}
		
		Valve.plainRefresh(valves, pipes);
	}
	
	private static void hidePipesBetweenInputs(List<Pipe> pipes) {
		
		for(Pipe pipe : pipes) {
			if(pipe.isPipeBetweenInputs()) {
				pipe.getGraphic().setFill(Constants.BACKGROUND_COLOR);
			}
		}
	}

	public static List<Valve> getValves() {
		return valves;
	}

	public static void setValves(List<Valve> valves) {
		Panel.valves = valves;
	}

	public static List<Pipe> getPipes() {
		return pipes;
	}

	public static void setPipes(List<Pipe> pipes) {
		Panel.pipes = pipes;
	}
}
