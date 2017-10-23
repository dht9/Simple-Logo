package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Left implements Command {

	private Command input;

	public Left(Command par1) {
		input = par1;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double result = input.execute(t, commands, variables);
		t.setHeading(t.getHeading() + result);
		return result;
	}

}
