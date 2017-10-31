package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetHeading implements Command {

	private static final long serialVersionUID = 7489917969522945122L;
	private Command input;
	
	public SetHeading(Command par1) {
		input = par1;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.setHeading(input, commands, variables);
	}

}
