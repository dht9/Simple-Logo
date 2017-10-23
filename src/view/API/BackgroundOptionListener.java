package view.API;

import javafx.scene.paint.Color;

/**
 * Class for detecting changes to background choice box.
 * 
 * @author DavidTran
 *
 */
public interface BackgroundOptionListener {

	/**
	 * Changes the background color.
	 */
	public void backgroundColorChange(Color color);
	
}
