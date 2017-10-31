package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Ask;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class AskBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Ask(dispenser.getNextCommandList(), dispenser.getNextCommandList());
	}

}
