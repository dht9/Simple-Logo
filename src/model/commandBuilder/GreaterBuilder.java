package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Greater;
import model.commands.Less;

public class GreaterBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Greater(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
