package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class PenDownP implements Command {

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if(t.getPenDown()) return 1;
		return 0;
	}

}
