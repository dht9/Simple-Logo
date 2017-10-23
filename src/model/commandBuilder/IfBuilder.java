package model.commandBuilder;

import java.util.ArrayList;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.IfElse;

public class IfBuilder implements CommandDef {
	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new IfElse(dispenser.getNextCommand(), dispenser.getNextCommandList(), new ArrayList<Command>());
	}

}
