package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.For;
import model.commands.NumberCommand;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class DoTimesBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new For(dispenser.getNextVariable(), new NumberCommand(1), dispenser.getNextCommand(), new NumberCommand(1),
				dispenser.getNextCommandList());
	}

}
