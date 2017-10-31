package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.Windows.VariableListener;

/**
 * Holds the known variables. This class encapsulates how local scopes work,
 * notifying listeners of changes, and saving/loading.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public class VariableManager {
	private HashMap<String, Double> globals = new HashMap<>();
	private List<Map<String, Double>> localScopes = new ArrayList<>();
	private List<VariableListener> listeners = new ArrayList<>();
	private boolean updated;

	/**
	 * @param s
	 *            The variable name
	 * @return The variable's value -- defaults to 0 the first time it is
	 *         referenced.
	 */
	public double getValue(String s) {
		if (getScope().containsKey(s))
			return getScope().get(s);
		if (globals.containsKey(s))
			return globals.get(s);
		setValue(s, 0);
		return 0;
	}

	/**
	 * Sets the local value if the variable exists in the local scope -- otherwise
	 * sets it globally.
	 * 
	 * @param s
	 *            The variable name
	 * @param val
	 *            The new value
	 * @throws SLogoException
	 *             If the variable name is invalid
	 */
	public void setValue(String s, double val) throws SLogoException {
		if (getScope().containsKey(s))
			setLocalValue(s, val);
		else
			setGlobalValue(s, val);
	}

	/**
	 * Sets the variable value locally.
	 * 
	 * @param s
	 *            The variable name
	 * @param val
	 *            The new value
	 * @throws SLogoException
	 *             If the variable name is invalid
	 */
	public void setLocalValue(String s, double val) throws SLogoException {
		if (!s.matches(Parser.SYNTAX.getString("Variable")))
			throw new SLogoException("InvalidVar", s);
		getScope().put(s, val);
		updated = true;
	}

	/**
	 * Sets the variable value globally.
	 * 
	 * @param s
	 *            The variable name
	 * @param val
	 *            The new value
	 * @throws SLogoException
	 *             If the variable name is invalid
	 */
	public void setGlobalValue(String s, double val) throws SLogoException {
		if (!s.matches(Parser.SYNTAX.getString("Variable")))
			throw new SLogoException("InvalidVar", s);
		updated = true;
		globals.put(s, val);
		updated = true;
	}

	/**
	 * Starts a new local scope
	 */
	public void enterLocalScope() {
		localScopes.add(new HashMap<>());
	}

	/**
	 * Deletes the last local scope
	 */
	public void leaveLocalScope() {
		localScopes.remove(localScopes.size() - 1);
	}

	/**
	 * @param listener
	 *            To be notified of any changes to variables
	 */
	public void addListener(VariableListener listener) {
		listeners.add(listener);
	}

	/**
	 * Notifies the listeners if a change has occurred since the last call to this
	 * method.
	 */
	public void notifyListeners() {
		if (updated) {
			updated = false;
			for (VariableListener listener : listeners) {
				listener.changedMap(globals);
			}
		}
	}

	private Map<String, Double> getScope() {
		if (localScopes.isEmpty())
			return globals;
		return localScopes.get(localScopes.size() - 1);
	}

	/**
	 * @param fileName The file to save variables to.
	 */
	public void save(String fileName) {
		SaverLoader.save(globals, fileName);
	}

	/**
	 * @param fileName The file to load variables from -- assumed to be valid.
	 */
	@SuppressWarnings("unchecked")
	public void load(String fileName) {
		HashMap<String, Double> data = (HashMap<String, Double>) SaverLoader.load(fileName);
		globals = new HashMap<>();
		for (String s : data.keySet()) {
			globals.put(s, data.get(s));
		}
		updated = true;
		notifyListeners();
	}
}
