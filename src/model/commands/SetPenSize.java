package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetPenSize implements Command {

	private static final long serialVersionUID = -2641483880650176017L;
	private Command size;
	
	public SetPenSize(Command size) {
		this.size = size;
	} 
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double sizeNum = size.execute(t, commands, variables);
		t.setPenSize(sizeNum);
		return sizeNum;
	}

}
