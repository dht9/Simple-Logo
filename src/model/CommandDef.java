package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandDef {

	private String name;
	private List<String> variables;
	private List<Command> commands;

	public CommandDef(String name, List<String> variables, List<Command> commands) {
		this.name = name;
		this.variables = variables;
		this.commands = commands;
	}

	public Command build(Command[] parameters) {
		if(parameters.length != numExpected())
			throw new RuntimeException("Unexpected number of arguments"); 
		
		Map<String, Command> localVars = new HashMap<String, Command>();
		
		return new UserDefinedCommand(localVars, commands);
	}

	public int numExpected() {
		return variables.size();
	}
}
