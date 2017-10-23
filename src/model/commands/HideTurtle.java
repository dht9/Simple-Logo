package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class HideTurtle implements Command {

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		t.setVisible(false);
		return 0;
	}

}
