package it.promec.pipes;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BackGround {

	public static GridPane create() {
		
		GridPane grid = new GridPane();
		grid.setHgap(0);
		grid.setVgap(0);
		
		boolean isWhite = true;
		
		for(int i=0; i<Constants.ROWS; i++) {
			for(int j=0; j<Constants.COLUMNS; j++) {
				
				Rectangle rectangle = new Rectangle();
				
				Color color;				
				//color = (isWhite) ? Color.rgb(100, 100, 100) : Color.rgb(130, 130, 130);
				color = Color.rgb(100, 100, 100);
				
				isWhite = !isWhite;

				rectangle.setFill(color);
				rectangle.setHeight(Constants.RECTANGLE_Y);
				rectangle.setWidth(Constants.RECTANGLE_X);
				grid.add(rectangle, j, i);
			}
		}
		
		addValvesAndPipes(grid);
		
		return grid;
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
					Valve valve = new Valve(j,i);
					grid.add(valve.getBackground(), j, i);
					grid.add(valve.getGraphic(), j, i);
				}
						
				if((i%2==1)&&(j%2==0)) {
					Pipe pipe = new Pipe(true);
					grid.add(pipe.getGraphic(), j, i);
				}
				
				if((i%2==0)&&(j%2==1)) {
					Pipe pipe = new Pipe(false);
					grid.add(pipe.getGraphic(), j, i);
				}
				
			}
		}
	}
}
