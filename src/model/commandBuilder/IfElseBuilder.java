package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.IfElse;

/**
 * Builds the IfElse Command with three arguments: The command representing the
 * condition, a list of commands for true values, and a list of commands for
 * false values.
 * 
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 *
 */
public class IfElseBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new IfElse(dispenser.getNextCommand(), dispenser.getNextCommandList(), dispenser.getNextCommandList());
	}

}
