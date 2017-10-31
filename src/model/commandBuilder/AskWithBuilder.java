package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.AskWith;
import model.commands.CommandList;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class AskWithBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new AskWith(new CommandList(dispenser.getNextCommandList()), dispenser.getNextCommandList());
	}

}
