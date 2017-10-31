package model.commands;

import java.util.ArrayList;
import java.util.List;

import model.Command;
import model.CommandManager;
import model.TurtleManager;
import model.TurtleManagerSpecificCommand;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Tell extends TurtleManagerSpecificCommand {

	private static final long serialVersionUID = -7345912902104085021L;
	private List<Command> ids;

	public Tell(List<Command> ids) {
		this.ids = ids;
	}
	
	@Override
	public double executeOnManager(TurtleManager t, CommandManager commands, VariableManager variables) {
		List<Integer> list = new ArrayList<>();
		int toReturn = 0;
		for(Command c : ids) {
			toReturn = (int)Math.round(c.execute(t, commands, variables));
			list.add(toReturn);
		}
		t.setActiveTurtles(list); 
		return toReturn;
	}

}
