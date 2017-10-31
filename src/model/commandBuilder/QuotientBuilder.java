package model.commandBuilder;

import model.Command;
import model.SLogoException;
import model.StackGroupCommandBuilder;
import model.TokenDispenser;
import model.commands.NumberCommand;
import model.commands.Pow;
import model.commands.Product;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class QuotientBuilder extends StackGroupCommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser, Command last) throws SLogoException {
		return new Product(last, new Pow(dispenser.getNextCommand(), new NumberCommand(-1)));
	}

}
