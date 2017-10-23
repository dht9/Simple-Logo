package model;

import java.util.ArrayList;
import java.util.List;

import view.API.StringListener;
import view.API.TurtleListener;
import view.API.VariableListener;

public class Model {
	
	private List<TurtleListener> turtleListeners;
	
	private CommandManager commands;
	private VariableManager variables;
	
	private List<Turtle> turtles;
	
	public Model(CommandManager commands) {
		turtleListeners = new ArrayList<TurtleListener>();
		this.commands = commands;
		variables = new VariableManager();
		turtles = new ArrayList<Turtle>();
	}
	
	public void setLanguage(String language) {
		commands.setLanguage(language);
	}
	
	public void addTurtleListener(TurtleListener tL) {
		turtleListeners.add(tL);
	}
	
	public void addVariableListener(VariableListener vL) {
		variables.addListener(vL);
	}
	
	public void addCommandListener(StringListener sL) {
		commands.addListener(sL);
	}
	
	public void addTurtle(Turtle t, TurtleListener tL) {
		turtles.add(t);
		t.addTurtleListener(tL);
		turtleListeners.add(tL);
	}
	
	public void execute(String code) throws SLogoException {
		Parser parser = new Parser(code, commands);
		while(parser.hasNextCommand()) {
			parser.getNextCommand().execute(turtles.get(0), commands, variables);
			variables.notifyListeners();
		}
	}
}
