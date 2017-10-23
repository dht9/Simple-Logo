package view.API;

import javafx.scene.paint.Color;

/**
 * Class for detecting changes to pen choice box.
 * 
 * @author taekwhunchung
 *
 */

public interface PenOptionListener {
	
	/**
	 * Changes the background color.
	 */

	void penColorChange(Color valueOf);

}
