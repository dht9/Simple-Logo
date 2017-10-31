package model.commands;

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
public class AskWith extends TurtleManagerSpecificCommand {
	private static final long serialVersionUID = 7931047379222341527L;
	private Command condition;
	private Command commandList;
	private double toReturn = 0;
	
	public AskWith(Command condition, List<Command> commands)
	{
		this.condition = condition;
		commandList = new CommandList(commands);
	}
	
	@Override
	public double executeOnManager(TurtleManager t, CommandManager commands, VariableManager variables) {
		t.askAll(turtle->executeAndUpdate(turtle, commands, variables));
		return toReturn;
	}

	private void executeAndUpdate(Turtle t, CommandManager commands, VariableManager variables) {
		if(condition.execute(t, commands, variables)!=0)
			toReturn = commandList.execute(t, commands, variables);
	}
}
