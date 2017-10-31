package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.NumberCommand;
import model.commands.Sin;
import model.commands.Sum;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class CosBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Sin(new Sum(dispenser.getNextCommand(), new NumberCommand(90)));
	}

}
