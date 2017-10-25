package view.API.TextAreaAPI;

/**
 * A Pane showing the current commands available to the user. It implements
 * StringListener so it can be updated directly when new commands are added.
 */
public interface UserDefinedCommandDisplay extends StringListener {
	/**
	 * Removes all known commands from the window
	 */
	public void clearCommands();
}
