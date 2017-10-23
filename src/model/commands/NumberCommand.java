package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class NumberCommand implements Command {

	private double input;
	
	public NumberCommand(double num) {
		input = num;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return input;
	}
	
}
