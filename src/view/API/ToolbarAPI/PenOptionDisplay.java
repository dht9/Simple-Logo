package view.API.ToolbarAPI;

import view.API.SubcomponentViewAPI;
import view.API.CommandIOAPI.TurtleListener;

/**
 * Allows detection of background color change.
 * 
 * @author taekwhunchung
 *
 */
public interface PenOptionDisplay extends SubcomponentViewAPI {

	/**
	 * Give a listener to the color option box to detect selections.
	 * 
	 * @param l
	 *            Listener that detects when user selects a color option
	 */
	public void addPenOptionListener(TurtleListener l);

}
