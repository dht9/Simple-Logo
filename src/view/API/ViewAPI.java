package view.API;

import java.util.function.Consumer;

import model.SLogoException;

public interface ViewAPI {
	
	/**
	 * Creates and displays the simulation window.
	 * 
	 * @param commandConsumer
	 */
	public void start(Consumer<String> commandConsumer);
	
	/**
	 * @return
	 * TurtleListener that detects changes to Turtle states.
	 */
	public TurtleListener getTurtleListener();

	/**
	 * @return
	 * VariableListener that detects changes to current variables.
	 */
	public VariableListener getVariableListener();

	/**
	 * @return
	 * StringListener that detects changes to list of commands.
	 */
	public StringListener getCommandListener();
	
	/**
	 * @return
	 * StringListener that detects changes to list of user commands.
	 */
	public StringListener getUserDefinedCommandListener();

	/**
	 * @param e A SLogoException produced when the code is executed
	 */
	void display(SLogoException e);
	
}
