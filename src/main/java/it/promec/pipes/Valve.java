package it.promec.pipes;

import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Valve {

	private boolean open = false;
	private Circle graphic;
	private Rectangle background;
	private int x;
	private int y;
	private static int numberOfFlows = 1;
	public static Color pipeOnColor = Color.CORAL;
	
	public Valve(int x, int y, List<Valve> valves, List<Pipe> pipes) {
		
		this.x = x;
		this.y = y;
		
		Circle circle = new Circle();
		circle.setFill(Constants.VALVE_OFF_COLOR);
		circle.setRadius(10);
		circle.setStyle("-fx-cursor: hand;"); //Hand pointer on mouse over
		circle.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	if(!open) {
            		System.out.println("Valve clicked x=" + x + " y=" + y);
                	open = true;
                	Color color = (open) ? Constants.VALVE_ON_COLOR : Constants.VALVE_OFF_COLOR;
            		circle.setFill(color);
            		circle.setStyle("-fx-cursor: none;"); //Hide hand pointer on mouse over
            		enlightPipes(x, y, valves, pipes);
            		enlightSpecialCases(x, y, valves, pipes);
            	}
            }
        });
		
		graphic = circle;
		
		Rectangle rectangle = new Rectangle();
		Color color = isInput() ? Constants.INPUT_BACKGROUND_COLOR : Constants.VALVE_BACKGROUND_COLOR;
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
							pipe.getGraphic().setFill(pipeOnColor);
							System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
				
				if((valve.getX()==x)&&(valve.getY() == y-2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getX() == x)&&((pipe.getY() == y-1))) {
							pipe.getGraphic().setFill(pipeOnColor);
							System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
				
				if((valve.getY()==y)&&(valve.getX() == x-2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getY() == y)&&((pipe.getX() == x-1))) {
							pipe.getGraphic().setFill(pipeOnColor);
							System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
				
				if((valve.getY()==y)&&(valve.getX() == x+2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getY() == y)&&((pipe.getX() == x+1))) {
							pipe.getGraphic().setFill(pipeOnColor);
							System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
			}
			
			hidePipesBetweenInputs(pipes);
			
		}
	}
	
	private void enlightSpecialCases(int x, int y, List<Valve> valves, List<Pipe> pipes) {
		
		
		//START Gestione tubo lungo
		boolean specialValveOn1 = false;
		boolean specialValveOn2 = false;
		
		if(y==7) {
			
			for(Valve valve : valves) {
				
				if(valve.getX()==7) {
					if(valve.isOpen())
						specialValveOn1 = true;
				}
				
				if(valve.getX()==13) {
					if(valve.isOpen())
						specialValveOn2 = true;
				}
			}
		}
		
		for(Pipe pipe: pipes) {
			if(specialValveOn1 && specialValveOn2) {
				if(pipe.getY() == Constants.ROWS-2) {
					if((pipe.getX() >= 8)&&(pipe.getX()<=12)) {
						pipe.getGraphic().setFill(pipeOnColor);
					}
				}
			}
		}
		//END Gestione tubo lungo
		
		
		boolean specialValveOn3 = false;
		boolean specialValveOn4 = false;
		
		//START Gestione tubo input a sinistra
		
		if(y==7) {
			
			for(Valve valve : valves) {
				
				if(valve.getX()==1) {
					if(valve.isOpen()) {
						specialValveOn3 = true;
					}		
				}
				
				if(valve.getX()==3) {
					if(valve.isOpen()) {
						specialValveOn4 = true;
					}	
				}
				
				
			}
		}
		
		
		if(specialValveOn3 && specialValveOn4) {
			System.out.println("Both open!");
		} else {
			System.out.println(specialValveOn3 + " " + specialValveOn4);
		}
		
		for(Pipe pipe: pipes) {
			if(specialValveOn3 && specialValveOn4) {
				if(pipe.getY() == Constants.ROWS-2) {
					if((pipe.getX() >= 1)&&(pipe.getX()<=3)) {
						pipe.getGraphic().setFill(pipeOnColor);
					}
				}
			}
		}
		
		//END Gestione tubo input a sinistra
	}

	private void hidePipesBetweenInputs(List<Pipe> pipes) {
		
		for(Pipe pipe : pipes) {
			if(pipe.isPipeBetweenInputs()) {
				pipe.getGraphic().setFill(Constants.BACKGROUND_COLOR);
			}
		}
	}

	public static void changePipeColor() {
		
		numberOfFlows++;
		if(numberOfFlows >=6) numberOfFlows = 1;
		
		switch (numberOfFlows) {
			
			case 1:	pipeOnColor = Color.CORAL;
								  break;
			case 2:	pipeOnColor = Color.AQUAMARINE;
			  					  break;
			case 3:	pipeOnColor = Color.FIREBRICK;
			  					  break;
			case 4:	pipeOnColor = Color.YELLOW;
			  					  break;
			case 5:	pipeOnColor = Color.HOTPINK;
			  					  break;
			
			default:	pipeOnColor = Color.PALEVIOLETRED;
			  						  break;
		}
	}
	
	public boolean isInput() {
		return ((x == Constants.COLUMNS - 2)||(x == 1 && y == 7));
	}
}
