package it.promec.pipes;

import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Valve {

	private boolean open = false;
	private boolean clickable = false;
	private Circle graphic;
	private Rectangle background;
	private int x;
	private int y;
	private static int xLastClicked;
	private static int yLastClicked;
	private static int numberOfFlows = 1;
	public static Color pipeOnColor = Color.CORAL;
	
	public Valve(int x, int y, List<Valve> valves, List<Pipe> pipes) {
		
		this.x = x;
		this.y = y;
		
		Circle circle = new Circle();
		circle.setFill(Constants.VALVE_OFF_COLOR);
		circle.setRadius(10);
		if(isInput()) {
			circle.setStyle("-fx-cursor: hand;"); //Hand pointer on mouse over
			clickable = true;
		}
		circle.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
            	if((!open)&&(clickable)) {
            		System.out.println("Valve clicked x=" + x + " y=" + y);
                	open = true;
                	clickable = false;
                	Color color = (open) ? Constants.VALVE_ON_COLOR : Constants.VALVE_OFF_COLOR;
            		circle.setFill(color);
            		circle.setStyle("-fx-cursor: none;"); //Hide hand pointer on mouse over
            		enlightPipes(x, y, valves, pipes);
            		enlightSpecialCases(x, y, valves, pipes);
            		findNewClickables(x, y, valves);
            		xLastClicked = x;
            		yLastClicked = y;
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

	public boolean isClickable() {
		return clickable;
	}

	public void setClickable(boolean clickable) {
		this.clickable = clickable;
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
	
	public static int getxLastClicked() {
		return xLastClicked;
	}

	public static void setxLastClicked(int xLastClicked) {
		Valve.xLastClicked = xLastClicked;
	}

	public static int getyLastClicked() {
		return yLastClicked;
	}

	public static void setyLastClicked(int yLastClicked) {
		Valve.yLastClicked = yLastClicked;
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
							//System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
				
				if((valve.getY()==y)&&(valve.getX() == x-2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getY() == y)&&((pipe.getX() == x-1))) {
							pipe.getGraphic().setFill(pipeOnColor);
							//System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
						}
					}
				}
				
				if((valve.getY()==y)&&(valve.getX() == x+2)) {
					for(Pipe pipe: pipes) {
						if((pipe.getY() == y)&&((pipe.getX() == x+1))) {
							pipe.getGraphic().setFill(pipeOnColor);
							//System.out.println("Enlighted pipe x=" + pipe.getX() + " y=" + pipe.getY());
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
				if(pipe.getY() == 7) {
					if((pipe.getX() >= 8)&&(pipe.getX()<=12)) {
						pipe.getGraphic().setFill(pipeOnColor);
					}
				}
			}
		}
		//END Gestione tubo lungo
		
	}

	private void hidePipesBetweenInputs(List<Pipe> pipes) {
		
		for(Pipe pipe : pipes) {
			if(pipe.isPipeBetweenInputs()) {
				pipe.getGraphic().setFill(Constants.BACKGROUND_COLOR);
			}
		}
	}

	public static void changePipeOnColor() {
		
		numberOfFlows++;
		if(numberOfFlows >=6) numberOfFlows = 1;
		
		switch (numberOfFlows) {
			
			case 1:	pipeOnColor = Color.CORAL;
								  break;
			case 2:	pipeOnColor = Color.YELLOW;
			  					  break;
			case 3:	pipeOnColor = Color.GREEN;
			  					  break;
			case 4:	pipeOnColor = Color.BLUE;
			  					  break;
			case 5:	pipeOnColor = Color.PINK;
			  					  break;
			
			default:	pipeOnColor = Color.BROWN;
			  						  break;
		}
	}
	
	public boolean isInput() {
		return ((x == Constants.COLUMNS - 2)||(x == 1 && y == 7));
	}
	
	private void findNewClickables(int x, int y, List<Valve> valves) {
		
		for(Valve valve: valves) {
		
			if(valve.getX() == x) {
				if((valve.getY() == y +2)||(valve.getY() == y -2)) {
					valve.setClickable(true);
					valve.getGraphic().setStyle("-fx-cursor: hand;");
				}
			}
			
			if(valve.getY() == y) {
				if((valve.getX() == x +2)||(valve.getX() == x -2)) {
					valve.setClickable(true);
					valve.getGraphic().setStyle("-fx-cursor: hand;");
				}
			}
			
			//Caso speciale
			if((x == 13)&&(y == 7)) {
				if((valve.getX()==7)&&(valve.getY()==7)) {
					valve.setClickable(true);
					valve.getGraphic().setStyle("-fx-cursor: hand;");
				}
			}
		}
	}
}
