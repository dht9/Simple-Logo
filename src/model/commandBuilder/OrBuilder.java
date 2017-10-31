package model.commandBuilder;

import model.Command;
import model.SLogoException;
import model.StackGroupCommandBuilder;
import model.TokenDispenser;
import model.commands.Or;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class OrBuilder extends StackGroupCommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser, Command last) throws SLogoException {
		return new Or(last, dispenser.getNextCommand());
	}

}
