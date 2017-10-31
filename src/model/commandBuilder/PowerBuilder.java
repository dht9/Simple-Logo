package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Pow;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class PowerBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Pow(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
