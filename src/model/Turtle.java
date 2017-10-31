package model;

/**
 * Represents a turtle or multiple turtles that can receive commands. For
 * multiple turtles, commands give the return value for the last active one.
 * 
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public interface Turtle {

	/**
	 * @return The id of the turtle
	 */
	public int getID();

	/**
	 * @return the x coordinate of the turtle
	 */
	public double getX();

	/**
	 * @return the y coordinate of the turtle
	 */
	public double getY();

	/**
	 * @return the heading of the turtle
	 */
	public double getHeading();

	/**
	 * @return whether the pen is done (ie should lines be drawn)
	 */
	public boolean getPenDown();

	/**
	 * @return whether the turtle is visible 
	 */
	public boolean isVisible();

	/**
	 * @return the index of the pen color being used
	 */
	public int getPenColorIndex();

	/**
	 * @return the pen size in pixels
	 */
	public double getPenSize();

	/**
	 * @return the index of the shape
	 */
	public int getShapeIndex();

	/**
	 * @param par The distance to move, as a command
	 * @param commands The available commands
	 * @param variables The available variables
	 * @return The distance moved
	 */
	public double forward(Command par, CommandManager commands, VariableManager variables);

	/**
	 * @param input The angle to turn, as a command
	 * @param commands The available commands
	 * @param variables The available variables
	 * @return The angle change
	 */
	public double left(Command input, CommandManager commands, VariableManager variables);

	/**
	 * @param input The target heading, as a command
	 * @param commands The available commands
	 * @param variables The available variables
	 * @return The angle change
	 */
	public double setHeading(Command input, CommandManager commands, VariableManager variables);

	/**
	 * @param xCor The target x coordinate, as a command
	 * @param yCor The target y coordinate, as a command
	 * @param commands The available commands
	 * @param variables The available variables
	 * @return The distance moved
	 */
	public double setXY(Command xCor, Command yCor, CommandManager commands, VariableManager variables);

	/**
	 * @param xCor The x to point to, as a command
	 * @param yCor The y to point to, as a command
	 * @param commands The available commands
	 * @param variables The available variables
	 * @return The angle change
	 */
	public double setTowards(Command xCor, Command yCor, CommandManager commands, VariableManager variables);

	/**
	 * @param b whether the pen should be down
	 */
	public void setPenDown(boolean b);

	/**
	 * @param index the index of the color to change to
	 */
	public void setPenColor(int index);

	/**
	 * @param pixels the new pen thickness, in pixels
	 */
	public void setPenSize(double pixels);

	/**
	 * @param index the index of the background color to change to
	 */
	public void setBackgroundColor(int index);

	/**
	 * @param b whether the turtle should be visible
	 */
	public void setVisible(boolean b);

	/**
	 * @param index The index of the shape to use
	 */
	public void setShapeIndex(int index);

	/**
	 * @return clears the lines from the screen
	 */
	public double clearScreen();

	/**
	 * @return the number total number of turtles in the model
	 */
	public int getNumTurtles();

	/**
	 * @param indexVal The index of the color to replace
	 * @param rVal 
	 * @param gVal
	 * @param bVal
	 */
	public void setPalette(int indexVal, int rVal, int gVal, int bVal);
}
