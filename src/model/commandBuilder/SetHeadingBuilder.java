package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.SetHeading;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetHeadingBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new SetHeading(dispenser.getNextCommand());
	}

}
