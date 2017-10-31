package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Less;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class LessBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Less(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
