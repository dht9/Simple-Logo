package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.NumberCommand;
import model.commands.SetXY;

public class HomeBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new SetXY(new NumberCommand(0), new NumberCommand(0));
	}

}
