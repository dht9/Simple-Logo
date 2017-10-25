package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.API.TextAreaAPI.VariableListener;

public class VariableManager {
	private Map<String, Double> globals = new HashMap<>();
	private List<Map<String, Double>> localScopes = new ArrayList<>();
	private List<VariableListener> listeners = new ArrayList<>();
	private boolean updated;
	
	public VariableManager() {
	}
	
	public double getValue(String s) {
		if(getScope().containsKey(s))
			return getScope().get(s);
		if(globals.containsKey(s))
			return globals.get(s);
		setValue(s, 0);
		return 0;
	}
	
	public void setValue(String s, double val) {
		if(getScope().containsKey(s))
			setLocalValue(s, val);
		else
			setGlobalValue(s, val);
	}
	
	public void setLocalValue(String s, double val) {
		if(!s.matches(Parser.SYNTAX.getString("Variable")))
			throw new SLogoException("InvalidVar", s);
		getScope().put(s, val);
	}
	
	public void setGlobalValue(String s, double val) {
		if(!s.matches(Parser.SYNTAX.getString("Variable")))
			throw new SLogoException("InvalidVar", s);
		updated = true;
		globals.put(s, val);
	}
	
	public void enterLocalScope() {
		localScopes.add(new HashMap<>());
	}
	
	public void leaveLocalScope() {
		localScopes.remove(localScopes.size()-1);
	}
	
	public void addListener(VariableListener listener) {
		listeners.add(listener);
	}
	
	public void notifyListeners() {
		if(updated) {
			updated = false;
			for(VariableListener listener : listeners) {
				listener.changedMap(Collections.unmodifiableMap(globals));
			}
		}
	}
	
	private Map<String, Double> getScope(){
		if(localScopes.isEmpty())
			return globals;
		return localScopes.get(localScopes.size()-1);
	}
}
