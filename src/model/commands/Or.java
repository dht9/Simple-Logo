package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Or implements Command {

	private static final long serialVersionUID = 2825735315668974704L;
	private Command x1;
	private Command x2;
	
	public Or(Command x1, Command x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if(x1.execute(t, commands, variables) != 0 || x2.execute(t, commands, variables) != 0) return 1;
		return 0;
	}

}
