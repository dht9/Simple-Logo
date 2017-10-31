package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class XCor implements Command {

	private static final long serialVersionUID = -3053705734473356831L;

	public XCor() {}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return t.getX();
	}

}
