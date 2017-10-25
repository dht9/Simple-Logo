package view.API.CommandIOAPI;

import javafx.scene.paint.Color;
import view.API.SubcomponentViewAPI;

/**
 * The Pane responsible for presenting the turtle the lines it has drawn, along
 * with some background. It implements TurtleListener so that it can be notified
 * directly when changes to the turtle's state occur.
 *
 */
public interface CanvasDisplay extends SubcomponentViewAPI {

	/**
	 * Passes a background color to canvas class.
	 * 
	 * @param c
	 *            color of the background
	 */
	public void setBackgroundColor(Color c);

}