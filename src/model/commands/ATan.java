package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class ATan implements Command {

	private Command a;
	
	public ATan(Command a) {
		this.a = a;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return Math.toDegrees(Math.atan(a.execute(t, commands, variables)));
	}

}
