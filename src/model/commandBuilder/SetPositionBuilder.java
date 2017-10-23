package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.SetPosition;

public class SetPositionBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new SetPosition(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
