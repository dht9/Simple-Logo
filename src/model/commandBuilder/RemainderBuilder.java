package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Remainder;

public class RemainderBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Remainder(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
