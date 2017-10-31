package model.commandBuilder;

import java.util.ArrayList;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.IfElse;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class IfBuilder implements CommandBuilder {
	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new IfElse(dispenser.getNextCommand(), dispenser.getNextCommandList(), new ArrayList<Command>());
	}

}
