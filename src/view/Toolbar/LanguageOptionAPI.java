package view.Toolbar;

/**
 * Allows detection of language change
 * 
 * @author taekwhunchung
 *
 */

public interface LanguageOptionAPI {

	/**
	 * Give a listener to the language option box to detect selections.
	 * 
	 * @param l
	 *            Listener that detects when user selects a language option
	 */
	public void addLanguageOptionListener(LanguageListener l);
}
