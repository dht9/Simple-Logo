package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.SetXY;

public class SetXYBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new SetXY(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
