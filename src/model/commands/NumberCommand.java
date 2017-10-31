package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class NumberCommand implements Command {

	private static final long serialVersionUID = 8312725661651529289L;
	private double input;
	
	public NumberCommand(double num) {
		input = num;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return input;
	}
	
}
