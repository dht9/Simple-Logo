package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class ClearScreen implements Command {
	private static final long serialVersionUID = 7518525842758579433L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		Command home = new SetXY(new NumberCommand(0), new NumberCommand(0));
		double dist = home.execute(t, commands, variables);
		t.clearScreen();
		return dist;
	}

}
