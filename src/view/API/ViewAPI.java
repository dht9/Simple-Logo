package view.API;

public interface ViewAPI {
	
	/**
	 * @return
	 * TurtleListener that detects changes to Turtle states.
	 */
	public TurtleListenerAPI getTurtleListener();

	/**
	 * @return
	 * VariableListener that detects changes to current variables.
	 */
	public VariableListenerAPI getVariableListener();

	/**
	 * @return
	 * StringListener that detects changes to list of commands.
	 */
	public StringListenerAPI getCommandListener();
}
