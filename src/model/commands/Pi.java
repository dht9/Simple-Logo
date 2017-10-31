package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Pi implements Command {

	private static final long serialVersionUID = -6438091825591642793L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return Math.PI;
	}

}
