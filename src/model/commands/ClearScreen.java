package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class ClearScreen implements Command {

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		Command home = new SetXY(new NumberCommand(0), new NumberCommand(0));
		double dist = home.execute(t, commands, variables);
		t.clearScreen();
		return dist;
	}

}
