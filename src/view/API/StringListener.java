package view.API;

import java.util.Map;

import model.CommandDef;

/**
 * Listens for the addition of new strings -- specifically used to get
 * representations of new commands
 */
public interface StringListener extends SubcomponentViewAPI {
	/**
	 * Called when a new string becomes relevant to the particular context
	 * 
	 * @param s
	 *            The new String
	 */
	public void changedMap(Map<String, CommandDef> newMap);
}