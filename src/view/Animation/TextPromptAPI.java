package view.Animation;

import javafx.scene.input.KeyCode;
import view.SubcomponentViewAPI;

/**
 * The pane containing a text field where users can enter commands to be sent to
 * the model. Must have access to a Consumer<String>.
 *
 */
public interface TextPromptAPI extends SubcomponentViewAPI {

	/**
	 * Called when a command needs to be added to prompt and run.
	 * 
	 * @param s
	 *            string to be add to text prompt
	 * @param parameter
	 */
	public void runCommand(String s, String params);

	/**
	 * Called when user enters key command.
	 * 
	 * @param code
	 *            KeyCode that is processed
	 */
	public void handleInput(KeyCode code);

}
