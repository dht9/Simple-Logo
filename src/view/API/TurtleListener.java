package view.API;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.ImmutableTurtle;

/**
 * Listens for modifications to a turtle's state -- its methods must be called
 * when these changes occur
 *
 */
public interface TurtleListener extends TurtleImageListener {
	
	/**
	 * Gives the listener a Turtle to keep track of the state
	 * @param turtle A turtle with an observable state
	 */
	public void setTurtle(ImmutableTurtle turtle);

	/**
	 * Called when the turtle moves coordinates
	 * @param new x
	 * @param new y
	 */
	public void locationChange(double newX, double newY);

	/**
	 * Called when the turtle turns
	 * @param dtheta change in heading
	 */
	public void headingChange(double dtheta);

	/**
	 * Called when the pen's state (up/down) is toggled
	 */
	public void penChange(boolean down);

	/**
	 * Called when the visibility is toggled
	 */
	public void visibilityChange(boolean isVisible);
	
	/**
	 * Called when the pen color is changed
	 */
	public void penColorChange(Color color);

	/**
	 * Called when the screen should be cleared
	 */
	public void clearScreen();
	
	/**
	 * Called when user presses key to move turtle
	 */
	public void handleInput(KeyCode code);

}

