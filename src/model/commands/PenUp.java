package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class PenUp implements Command {

	private static final long serialVersionUID = 5018044383107238726L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		t.setPenDown(false);
		return 1;
	}
	
}
