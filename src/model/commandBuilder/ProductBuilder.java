package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Product;

public class ProductBuilder implements CommandDef{

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Product(dispenser.getNextCommand(), dispenser.getNextCommand());
	}

}
