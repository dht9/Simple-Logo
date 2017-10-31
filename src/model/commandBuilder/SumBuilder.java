package model.commandBuilder;

import model.Command;
import model.SLogoException;
import model.StackGroupCommandBuilder;
import model.TokenDispenser;
import model.commands.Sum;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SumBuilder extends StackGroupCommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser, Command last) throws SLogoException {
		return new Sum(last, dispenser.getNextCommand());
	}

}
