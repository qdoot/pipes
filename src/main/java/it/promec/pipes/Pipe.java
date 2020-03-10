package it.promec.pipes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pipe {

	private Rectangle graphic;
	private int x;
	private int y;
	
	public Pipe(int x, int y, boolean isVertical) {
		
		this.x = x;
		this.y = y;
		
		Rectangle pipe = new Rectangle();
		Color color = Constants.PIPE_OFF_COLOR;
		
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
