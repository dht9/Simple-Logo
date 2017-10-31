package view;

import javafx.scene.Parent;

/**
 * Methods for view subcomponents in the user interface
 * 
 * @author DavidTran
 *
 */
public interface SubcomponentViewAPI {

	/**
	 * Used for adding subcomponents into the interface.
	 * 
	 * @return
	 * Parent node containing the subcomponent.
	 */
	public Parent getParent();
}
