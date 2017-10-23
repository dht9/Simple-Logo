package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.To;

public class ToBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new To(dispenser.getNextToken(), dispenser.getNextVariableList(), dispenser.getNextCommandList());
	}

}
