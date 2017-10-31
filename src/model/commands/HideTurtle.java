package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class HideTurtle implements Command {
	private static final long serialVersionUID = -4655985376422334442L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		t.setVisible(false);
		return 0;
	}

}
