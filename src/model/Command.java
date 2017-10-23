package model;

/**
 * Represents a single line of SLogo code that can be executed. 
 *
 */
public interface Command {
	/**
	 * @param t
	 *            The turtle that this command may modify or get data from
	 * @param commands
	 *            The list of available commands, to which this may add
	 * @param variables
	 *            The list of variables, which this may alter or add to
	 * @return The result of the command this represents
	 */
	public double execute(Turtle t, CommandManager commands, VariableManager variables);
}
