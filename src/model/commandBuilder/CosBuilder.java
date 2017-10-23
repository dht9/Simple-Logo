package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.NumberCommand;
import model.commands.Sin;
import model.commands.Sum;

public class CosBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Sin(new Sum(dispenser.getNextCommand(), new NumberCommand(90)));
	}

}
