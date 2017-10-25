package view.API.TextAreaAPI;

import java.util.Map;
import java.util.Set;

import model.CommandDef;
import view.API.SubcomponentViewAPI;

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
	public void changedMap(Set<String> set, Set<String> commandSet);
	
}
