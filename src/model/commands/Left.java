package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Left implements Command {

	private static final long serialVersionUID = -6316260648514400919L;
	private Command input;

	public Left(Command par1) {
		input = par1;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.left(input, commands, variables);
	}

}
