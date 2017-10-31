package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Equal;
import model.commands.Not;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class NotEqualBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Not(new Equal(dispenser.getNextCommand(), dispenser.getNextCommand()));
	}
}
