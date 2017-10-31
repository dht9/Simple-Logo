package view.Windows;

import java.util.List;
import java.util.Map;
import java.util.Set;

import view.SubcomponentViewAPI;

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
	public void changedMap(Set<String> set, Map<String, List<String>> userCs);
	
}
