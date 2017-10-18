package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.API.StringListenerAPI;
import view.API.TurtleListenerAPI;
import view.API.VariableListenerAPI;

import view.StringListener;
import view.TurtleListener;
import view.VariableListener;

public class Model {
	
	private List<TurtleListenerAPI> turtleListeners;
	private List<VariableListenerAPI> variableListeners;
	private List<StringListenerAPI> commandListeners;
	
	private Map<String, CommandDef> commands; 		//choose type of collection
	private Map<String, Double> variables;
	
	public Model() {
		
		turtleListeners = new ArrayList<TurtleListenerAPI>();
		variableListeners = new ArrayList<VariableListenerAPI>();
		commandListeners = new ArrayList<StringListenerAPI>();
		
		commands = new HashMap<String, CommandDef>();
		variables = new HashMap<String, Double>();
		
	}
	
	public void addTurtleListener(TurtleListenerAPI tL) {
		turtleListeners.add(tL);
	}
	
	public void addVariableListener(VariableListenerAPI vL) {
		variableListeners.add(vL);
	}
	
	public void addCommandListener(StringListenerAPI sL) {
		commandListeners.add(sL);
	}
	
	public void execute(String code) {
		Parser parser = new Parser(code, commands);
		
	}

}
