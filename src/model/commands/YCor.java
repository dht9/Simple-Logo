package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class YCor implements Command {

	private static final long serialVersionUID = -8805640596637749805L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.getY();
	}

}
