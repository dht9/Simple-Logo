package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Pow implements Command {

	private static final long serialVersionUID = -8539321994182938109L;
	private Command base;
	private Command power;
	
	public Pow(Command base, Command power) {
		this.base = base;
		this.power = power;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return Math.pow(base.execute(t, commands, variables), power.execute(t, commands, variables));
	}

}
