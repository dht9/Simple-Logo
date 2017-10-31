package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Log implements Command {

	private static final long serialVersionUID = -6564968783754638512L;
	private Command a;
	
	public Log(Command a) {
		this.a = a;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return Math.log(a.execute(t, commands, variables));
	}

}
