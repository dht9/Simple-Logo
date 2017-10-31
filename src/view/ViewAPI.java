package view;

import java.util.function.Consumer;

import view.Animation.TurtleListener;
import view.Windows.StringListener;
import view.Windows.VariableListener;

public interface ViewAPI {

	/**
	 * Creates and displays the simulation window.
	 * 
	 * @param commandConsumer
	 */
	public void start(Consumer<String> commandConsumer);

	/**
	 * @return VariableListener that detects changes to current variables.
	 */
	public VariableListener getVariableListener();

	/**
	 * @return StringListener that detects changes to list of commands.
	 */
	public StringListener getCommandListener();

	/**
	 * @return StringListener that detects changes to list of user commands.
	 */
	public StringListener getUserDefinedCommandListener();

	/**
	 * Called when model needs the turtlelistener when a turtle is created.
	 * 
	 * @return TurtleListener
	 */
	public TurtleListener getNewTurtleListener();

	/**
	 * Called when turtle is created in model to display turtle stats
	 * 
	 * @return TurtleListener
	 */
	public TurtleListener getStateViewListener();
	
}
