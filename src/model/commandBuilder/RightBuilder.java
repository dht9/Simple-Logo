package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Left;
import model.commands.NumberCommand;
import model.commands.Product;

public class RightBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Left(new Product(new NumberCommand(-1), dispenser.getNextCommand()));
	}

}
