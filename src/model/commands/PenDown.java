package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class PenDown implements Command {

	private static final long serialVersionUID = 5423985229675509890L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		t.setPenDown(true);
		return 1;
	}

}
