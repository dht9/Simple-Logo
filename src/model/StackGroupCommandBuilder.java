package model;

/**
 * Represents CommandBuilders for commands where buildGroup should behave
 * differently. Specifically, buildGroup creates nested instances of the
 * command. This only makes sense for certain operations. As an example: ( sum
 * 10 20 30 40 ) yields a sum of all four.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public abstract class StackGroupCommandBuilder implements CommandBuilder {

	/* (non-Javadoc)
	 * @see model.CommandBuilder#build(model.TokenDispenser)
	 */
	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return build(dispenser, dispenser.getNextCommand());
	}

	/* (non-Javadoc)
	 * @see model.CommandBuilder#buildGroup(model.TokenDispenser)
	 */
	@Override
	public Command buildGroup(TokenDispenser dispenser) throws SLogoException {
		Command command = build(dispenser);
		while (!dispenser.peek().matches(Parser.SYNTAX.getString("GroupEnd"))) {
			command = build(dispenser, command);
		}
		return command;
	}

	/**
	 * @param dispenser The typical TokenDispenser
	 * @param last The first parameter -- used to allow nesting these commands in groups
	 * @return The built Command
	 * @throws SLogoException If the parameters from dispenser are invalid/unexpected
	 */
	protected abstract Command build(TokenDispenser dispenser, Command last) throws SLogoException;
}
