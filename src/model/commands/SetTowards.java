package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetTowards implements Command {

	private static final long serialVersionUID = -6328856068244155700L;
	private Command x;
	private Command y;

	public SetTowards(Command xCor, Command yCor) {
		x = xCor;
		y = yCor;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.setTowards(x, y, commands, variables);
	}
}
