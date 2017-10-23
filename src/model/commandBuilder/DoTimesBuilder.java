package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.For;
import model.commands.NumberCommand;

public class DoTimesBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new For(dispenser.getNextVariable(), new NumberCommand(1), dispenser.getNextCommand(), new NumberCommand(1),
				dispenser.getNextCommandList());
	}

}
