package model.commands;

import java.util.List;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * Represents an IfElse statement with a condition and two possible lists of
 * commands to run. Note, as is typical of Commands, this Command stores and
 * executes other Commands when it itself is executed.
 */
public class IfElse implements Command {

	private static final long serialVersionUID = 4182334857279998120L;
	private Command expr;
	private List<Command> trueCommands;
	private List<Command> falseCommands;

	/**
	 * @param expr
	 *            The condition to execute the true commands
	 * @param trueCommands
	 *            executed if expr is not 0 when execute is called
	 * @param falseCommands
	 *            executed if expr is exactly 0 when execute is called
	 */
	public IfElse(Command expr, List<Command> trueCommands, List<Command> falseCommands) {
		this.expr = expr;
		this.trueCommands = trueCommands;
		this.falseCommands = falseCommands;
	}

	/**
	 * Runs the ifelse command. Executes the condition first, then the appropriate
	 * list of commands based on that result.
	 */
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double ret = 0;
		if (expr.execute(t, commands, variables) != 0) {
			for (Command c : trueCommands)
				ret = c.execute(t, commands, variables);
		} else {
			for (Command c : falseCommands)
				ret = c.execute(t, commands, variables);
		}
		return ret;
	}

}
