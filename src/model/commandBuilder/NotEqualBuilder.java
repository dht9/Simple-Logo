package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.NotEqual;

public class NotEqualBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new NotEqual(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
