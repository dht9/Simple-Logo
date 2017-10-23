package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class VariableCommand implements Command {

	private String name;

	public VariableCommand(String name) {
		this.name = name;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return variables.getValue(name);
	}

}
