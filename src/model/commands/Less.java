package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Less implements Command {

	private Command input1;
	private Command input2;

	public Less(Command x1, Command x2) {
		input1 = x1;
		input2 = x2;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if (input1.execute(t, commands, variables) < input2.execute(t, commands, variables))
			return 1;
		return 0;
	}

}
