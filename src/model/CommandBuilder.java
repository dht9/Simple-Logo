package model;

import java.util.ArrayList;
import java.util.List;

import model.commands.CommandList;

/**
 * This interface defines a factory for Commands -- each implementation builds a
 * particular command. Most of the responsibilities of Parsing are delegated to
 * these classes by Parser.
 * 
 * The implementations of CommandBuilder hide a key feature of Parsing: it's
 * recursive. Builders that take arguments call getNext[something] from Parser.
 * 
 * See IfElseBuilder as an example implementation
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public interface CommandBuilder {
	/**
	 * Constructs a single command. Assumes that the command name was the last
	 * token, so that the tokens following are the parameters.
	 * 
	 * @param dispenser
	 *            A TokenDispenser to get parameters from
	 * @return A Command that can be executed, containing any needed parameters.
	 * @throws SLogoException
	 *             When encountering inappropriate command parameters.
	 */
	public Command build(TokenDispenser dispenser) throws SLogoException;

	/**
	 * Meant for constructing a group based on this command -- stops at the
	 * associated GroupEnd.
	 */
	public default Command buildGroup(TokenDispenser dispenser) throws SLogoException {
		List<Command> commands = new ArrayList<>();
		while (!dispenser.peek().matches(Parser.SYNTAX.getString("GroupEnd"))) {
			commands.add(build(dispenser));
		}
		return new CommandList(commands);
	}
}
