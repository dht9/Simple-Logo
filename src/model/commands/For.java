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
public class For implements Command {

	private static final long serialVersionUID = 1L;
	private String varName;
	private Command start;
	private Command end;
	private Command increment;
	private List<Command> commandList;
	
	public For(String variable, Command start, Command end, Command increment, List<Command> input) {
		varName = variable;
		this.start = start;
		this.end = end;
		this.increment = increment;
		commandList = input;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double ret = 0;
		double st = start.execute(t, commands, variables);
		double inc = increment.execute(t, commands, variables);
		double en = end.execute(t, commands, variables);
		for(double i = st; i <= en; i += inc) {
			variables.setValue(varName, i);
			for(Command command : commandList)
				ret = command.execute(t, commands, variables);
		}
		return ret;
	}

}
