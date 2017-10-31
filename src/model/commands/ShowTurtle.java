package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class ShowTurtle implements Command {

	private static final long serialVersionUID = 307984746123238206L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		t.setVisible(true);
		return 1;
	}

}
