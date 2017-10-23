package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class Make implements Command {

	private String variable;
	private Command value;
	
	public Make(String name, Command input) {
		variable = name;
		value = input;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double val = value.execute(t, commands, variables);
		variables.setValue(variable, val);
		return val;
	}

}
