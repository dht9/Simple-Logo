package view.API;

/**
 * A Pane showing the current variables available to the user. It implements
 * VariableListener so it can be updated directly when new commands are added.
 */
public interface VariableDisplayAPI extends VariableListenerAPI{
	/**
	 * Removes all known variables from the window
	 */
	public void clearVariables();
}
