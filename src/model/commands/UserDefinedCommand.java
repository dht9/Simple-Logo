package model.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandManager;
import model.SLogoException;
import model.Turtle;
import model.VariableManager;
import model.commandBuilder.CommandDef;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class UserDefinedCommand implements Command {
	private static final long serialVersionUID = -4720749543660734895L;
	private String name;
	private List<Command> parameters;
	
	public UserDefinedCommand(String name, List<Command> parameters) {
		this.name = name;
		this.parameters = parameters;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		CommandDef command;
		try{
			command = commands.getUserDefinedCommand(name);
		} catch(SLogoException e) {
			throw new SLogoException("DefChange", name);
		}
		Map<String, Double> vals = new HashMap<String, Double>();
		for(int i = 0; i < parameters.size(); i++) {
			vals.put(command.getParameterNames().get(i), 
					parameters.get(i).execute(t, commands, variables));
		}
		variables.enterLocalScope();
		for(String var : vals.keySet())
			variables.setLocalValue(var, vals.get(var));
		double answer = command.getCommandList().execute(t, commands, variables);
		variables.leaveLocalScope();
		return answer;
	}
}
