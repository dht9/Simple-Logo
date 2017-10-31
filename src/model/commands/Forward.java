package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Forward implements Command {

	private static final long serialVersionUID = 3383171923769027257L;
	private Command input;

	public Forward(Command par1) {
		input = par1;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.forward(input, commands, variables);
	}

}
