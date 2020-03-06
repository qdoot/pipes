package it.promec.pipes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pipe {

	private Rectangle graphic;
	
	public Pipe(boolean isVertical) {
		
		Rectangle pipe = new Rectangle();
		Color color = Color.BLACK;
		
		if(isVertical) {
			pipe.setHeight(Constants.RECTANGLE_Y/4);
			pipe.setWidth(Constants.RECTANGLE_X);
		} else {
			pipe.setHeight(Constants.RECTANGLE_Y);
			pipe.setWidth(Constants.RECTANGLE_X/4);
		}
		
		pipe.setFill(color);
		
		graphic = pipe;
	}

	public Rectangle getGraphic() {
		return graphic;
	}

	public void setGraphic(Rectangle graphic) {
		this.graphic = graphic;
	}
}
