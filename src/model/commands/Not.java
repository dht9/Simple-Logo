package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Not implements Command {

	private Command x1;
	
	public Not(Command x1) {
		this.x1 = x1;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if(x1.execute(t, commands, variables) == 0) return 1;
		return 0;
	}

}
