package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Sin implements Command {

	private Command x;
	
	public Sin(Command x) {
		this.x = x;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return Math.sin(Math.toRadians(x.execute(t, commands, variables)));
	}

	
	
}
