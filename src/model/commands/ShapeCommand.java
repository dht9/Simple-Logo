package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class ShapeCommand implements Command {

	private static final long serialVersionUID = 6831035105706814644L;

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.getShapeIndex();
	}

}
