package view.API;

/**
 * A Pane showing the current commands available to the user. It implements
 * StringListener so it can be updated directly when new commands are added.
 */
public interface UserDefinedCommandDisplayAPI extends StringListenerAPI {
	/**
	 * Removes all known commands from the window
	 */
	public void clearCommands();
}
