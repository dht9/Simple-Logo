package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Forward;
import model.commands.NumberCommand;
import model.commands.Product;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class BackBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Product(new NumberCommand(-1), new Forward(new Product(new NumberCommand(-1), dispenser.getNextCommand())));
	}

}
