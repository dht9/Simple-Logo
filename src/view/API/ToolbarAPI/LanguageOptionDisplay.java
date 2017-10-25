package view.API.ToolbarAPI;

import view.API.SubcomponentViewAPI;

/**
 * Allows detection of language change
 * 
 * @author taekwhunchung
 *
 */

public interface LanguageOptionDisplay extends SubcomponentViewAPI {

	/**
	 * Give a listener to the language option box to detect selections.
	 * 
	 * @param l
	 *            Listener that detects when user selects a language option
	 */
	public void addLanguageOptionListener(LanguageListener l);
}
