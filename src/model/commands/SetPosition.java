package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetPosition implements Command {

	private static final long serialVersionUID = 7061776636289024642L;
	private Command xCor;
	private Command yCor;

	public SetPosition(Command xCor, Command yCor) {
		this.xCor = xCor;
		this.yCor = yCor;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.setXY(xCor, yCor, commands, variables);
	}

}
