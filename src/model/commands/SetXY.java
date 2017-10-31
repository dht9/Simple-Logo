package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetXY implements Command {

	private static final long serialVersionUID = -55153100404545620L;
	private Command x;
	private Command y;
	
	public SetXY(Command newX, Command newY) {
		x = newX;
		y = newY;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.setXY(x, y, commands, variables);
	}

}
