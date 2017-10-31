package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Product implements Command {

	private static final long serialVersionUID = -2577392820254437807L;
	private Command first;
	private Command second;
	
	public Product(Command first, Command second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return first.execute(t, commands, variables)*second.execute(t, commands, variables);
	}

}
