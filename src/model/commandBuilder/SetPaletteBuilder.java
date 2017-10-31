package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.SetPalette;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetPaletteBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new SetPalette(dispenser.getNextCommand(), dispenser.getNextCommand(), dispenser.getNextCommand(),
				dispenser.getNextCommand());
	}

}
