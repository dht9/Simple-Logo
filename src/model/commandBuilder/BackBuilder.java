package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Forward;
import model.commands.NumberCommand;
import model.commands.Product;

public class BackBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Forward(new Product(new NumberCommand(-1), dispenser.getNextCommand()));
	}

}
