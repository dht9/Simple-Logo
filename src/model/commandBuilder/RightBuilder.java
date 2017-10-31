package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Left;
import model.commands.NumberCommand;
import model.commands.Product;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class RightBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Product(new NumberCommand(-1), new Left(new Product(new NumberCommand(-1), dispenser.getNextCommand())));
	}

}
