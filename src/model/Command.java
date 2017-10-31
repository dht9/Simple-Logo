package model;

import java.io.Serializable;

/**
 * Represents a single line of SLogo code that can be executed. Implementations
 * represent different kinds of commands.
 * 
 * See @IfElse for an example.
 *
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 *
 */
public interface Command extends Serializable {
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
