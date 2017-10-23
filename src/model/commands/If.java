package model.commands;

import java.util.ArrayList;
import java.util.List;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class If implements Command {

	private Command expr;
	private List<Command> input;
	
	public If(Command expr, List<Command> input) {
		this.expr = expr;
		this.input = input;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		List<Command> falseCommands = new ArrayList<Command>();
		falseCommands.add(new NumberCommand(0));
		IfElse useIfElse = new IfElse(expr, input, falseCommands);
		return useIfElse.execute(t, commands, variables);
	}

	
	
	
}
