package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Forward implements Command {

	private Command input;

	public Forward(Command par1) {
		input = par1;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double result = input.execute(t, commands, variables);
		t.setXY(t.getX() - result * Math.sin(Math.toRadians(t.getHeading())),
				t.getY() + result * Math.cos(Math.toRadians(t.getHeading())));
		return result;
	}

}
