package view.API.CommandIOAPI;

import view.API.SubcomponentViewAPI;

/**
 * The pane containing a text field where users can enter commands to be sent to
 * the model. Must have access to a Consumer<String>.
 *
 */
public interface TextPromptDisplay extends SubcomponentViewAPI {

	/**
	 * Called when a command needs to be added to prompt and run.
	 * 
	 * @param s
	 *            string to be add to text prompt
	 */
	public void runCommand(String s);
}
