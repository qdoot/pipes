package it.promec.pipes;

import javafx.scene.paint.Color;

public class Constants {

	public static final int ROWS = 11;
	public static final int COLUMNS = 17;
	public static final int RECTANGLE_X = 50;
	public static final int RECTANGLE_Y = RECTANGLE_X;
	public static final int WINDOW_X = (RECTANGLE_X * (COLUMNS + 3)) - 9;
	public static final int WINDOW_Y = (RECTANGLE_Y * ROWS) - 11;
	public static final int NODES_ON_X = 4;
	public static final int NODES_ON_Y = 5;
	public static final Color BACKGROUND_COLOR = Color.DIMGRAY;
	public static final Color PIPE_OFF_COLOR = Color.BLACK;
	public static final Color VALVE_OFF_COLOR = Color.DARKGREEN;
	public static final Color VALVE_ON_COLOR = Color.LIGHTGREEN;
	public static final Color VALVE_BACKGROUND_COLOR = Color.DARKGRAY;
	public static final Color INPUT_BACKGROUND_COLOR = Color.DARKRED;
}
