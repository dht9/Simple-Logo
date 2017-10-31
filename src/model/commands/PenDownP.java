package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class PenDownP implements Command {

	private static final long serialVersionUID = 4694739404063470209L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if(t.getPenDown()) return 1;
		return 0;
	}

}
