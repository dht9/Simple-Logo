package model.commands;

import java.util.List;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class CommandList implements Command {
	private static final long serialVersionUID = -7771505214481274902L;
	private List<Command> commandList;

	public CommandList(List<Command> commandList) {
		this.commandList = commandList;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double result = 0;
		for(Command com : commandList)
			result = com.execute(t, commands, variables);
		return result;
	}
}
