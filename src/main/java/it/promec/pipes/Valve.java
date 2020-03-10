package it.promec.pipes;

import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Valve {

	private boolean open;
	private Circle graphic;
	private Rectangle background;
	private int x;
	private int y;
	
	public Valve(int x, int y, List<Valve> valves, List<Pipe> pipes) {
		
		this.x = x;
		this.y = y;
		
		Circle circle = new Circle();
		circle.setFill(Color.DARKGREEN);
		circle.setRadius(10);
		circle.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	System.out.println("Valve clicked x=" + x + " y=" + y);
            	open = !open;
            	Color color = (open) ? Color.LIGHTGREEN : Color.DARKGREEN;
        		circle.setFill(color);
        		enlightPipes(x, y, valves, pipes);
            }
        });
		
		graphic = circle;
		
		
		Rectangle rectangle = new Rectangle();
		Color color = Color.DARKGRAY;
		rectangle.setFill(color);
		rectangle.setHeight(Constants.RECTANGLE_Y);
		rectangle.setWidth(Constants.RECTANGLE_X);
		
		background = rectangle;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Circle getGraphic() {
		return graphic;
	}

	public void setGraphic(Circle graphic) {
		this.graphic = graphic;
	}

	public Rectangle getBackground() {
		return background;
	}

	public void setBackground(Rectangle background) {
		this.background = background;
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
	
	private void enlightPipes(int x, int y, List<Valve> valves, List<Pipe> pipes) {
		
		for(Valve valve: valves) {
			
			if(valve.isOpen()) {
				
				if((valve.getX()==x)&&(valve.getY() == y+2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getX() == x)&&((pipe.getY() == y+1))) {
							pipe.getGraphic().setFill(Color.BLUEVIOLET);
							System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
				
				if((valve.getX()==x)&&(valve.getY() == y-2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getX() == x)&&((pipe.getY() == y-1))) {
							pipe.getGraphic().setFill(Color.BLUEVIOLET);
							System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
				
				if((valve.getY()==y)&&(valve.getX() == x-2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getY() == y)&&((pipe.getX() == x-1))) {
							pipe.getGraphic().setFill(Color.BLUEVIOLET);
							System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
				
				if((valve.getY()==y)&&(valve.getX() == x+2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getY() == y)&&((pipe.getX() == x+1))) {
							pipe.getGraphic().setFill(Color.BLUEVIOLET);
							System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
			}
		}
	}
}
