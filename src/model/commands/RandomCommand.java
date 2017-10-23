package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class RandomCommand implements Command {

	private Command max;
	
	public RandomCommand(Command max) {
		this.max = max;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return Math.random()*max.execute(t, commands, variables);
	}
}
