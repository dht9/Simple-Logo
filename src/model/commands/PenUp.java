package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class PenUp implements Command {

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		t.setPenDown(false);
		return 1;
	}
	
}
