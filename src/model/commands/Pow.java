package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Pow implements Command {

	private Command base;
	private Command power;
	
	public Pow(Command base, Command power) {
		this.base = base;
		this.power = power;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return Math.pow(base.execute(t, commands, variables), power.execute(t, commands, variables));
	}

}
