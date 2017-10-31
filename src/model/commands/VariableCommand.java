package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class VariableCommand implements Command {

	private static final long serialVersionUID = -488982445423278418L;
	private String name;

	public VariableCommand(String name) {
		this.name = name;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return variables.getValue(name);
	}

}
