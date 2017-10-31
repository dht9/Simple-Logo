package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class ShowingP implements Command {

	private static final long serialVersionUID = -5590913794825746037L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if(t.isVisible()) return 1;
		return 0;
	}

	
	
}
