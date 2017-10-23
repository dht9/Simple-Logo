package view.API;

public interface ImageOptionDisplay extends SubcomponentViewAPI{

	/**
	 * Give a listener to the color option box to detect selections.
	 * 
	 * @param l
	 *            Listener that detects when user selects a color option.
	 */
	public void addTurtleImageListener(TurtleImageListener l);
	
}
