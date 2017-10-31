package model.commands;

import java.util.ArrayList;
import java.util.List;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.TurtleManager;
import model.TurtleManagerSpecificCommand;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class Ask extends TurtleManagerSpecificCommand {
	private static final long serialVersionUID = 4697243747785822209L;
	private Command commandList;
	private List<Command> ids;
	private double toReturn = 0;

	public Ask(List<Command> ids, List<Command> commandList) {
		this.ids = ids;
		this.commandList = new CommandList(commandList);
	}
	
	@Override
	public double executeOnManager(TurtleManager t, CommandManager commands, VariableManager variables) {
		List<Integer> list = new ArrayList<>();
		for(Command c : ids) {
			list.add((int) Math.round(c.execute(t, commands, variables)));
		}
		t.ask(list, turtle->executeAndUpdate(turtle, commands, variables)); 
		return toReturn;
	}
	
	private void executeAndUpdate(Turtle t, CommandManager commands, VariableManager variables) {
		toReturn = commandList.execute(t, commands, variables);
	}

}
