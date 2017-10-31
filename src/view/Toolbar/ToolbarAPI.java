package view.Toolbar;

/**
 * Methods for view components in Toolbar.
 * 
 * @author taekwhunchung
 *
 */

public interface ToolbarAPI {

	/**
	 * method used to get BackgroundOptionView to then call .makeChoiceBox() method
	 * when starting or loading application.
	 * 
	 * @return BackgroundOptionView in ToolbarView
	 */
	public BackgroundOptionView getBackgroundOptionView();

	/**
	 * method used to get PenOptionView to then call .makeChoiceBox() method when
	 * starting or loading application.
	 * 
	 * @return PenOptionView in ToolbarView
	 */
	public PenOptionView getPenOptionView();

	/**
	 * method used to get LanguageOptionView to then call
	 * .addLanguageOptionListener.
	 * 
	 * @return PenButtons in ToolbarView
	 */
	public LanguageOptionView getLanguageOptionView();

	/**
	 * method used to get WorkSpaceButtons to update file load list
	 * 
	 * @return PenButtons in ToolbarView
	 */
	public WorkSpaceButtons getWorkSpaceButtons();
}
