package model.commands;

import java.util.List;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;
import model.commandBuilder.CommandDef;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class To implements Command {

	private static final long serialVersionUID = 588849466683654678L;
	private String name;
	private List<String> localVariables;
	private List<Command> inputCommands;
	
	public To(String name, List<String> localVariables, List<Command> commands) {
		this.name = name;
		this.localVariables = localVariables;
		inputCommands = commands;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if(commands.checkIfBuiltIn(name)) return 0;				//can throw error if we want
		commands.put(name, new CommandDef(name, localVariables, inputCommands));
		return 1;
	}

}
