package view.API;

/**
 * Allows detection of background color change.
 * 
 * @author DavidTran
 *
 */
public interface BackgroundOptionDisplay extends SubcomponentViewAPI {

	/**
	 * Give a listener to the color option box to detect selections.
	 * 
	 * @param l
	 *            Listener that detects when user selects a color option.
	 */
	public void addBackgroundOptionListener(BackgroundOptionListener l);
}
