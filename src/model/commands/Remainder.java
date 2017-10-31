package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Remainder implements Command {

	private static final long serialVersionUID = 7061576022464306697L;
	private Command x1;
	private Command x2;
	
	public Remainder(Command x1, Command x2) {
		this.x1 = x1;
		this.x2 = x2;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return x1.execute(t, commands, variables) % x2.execute(t, commands, variables);
	}
}