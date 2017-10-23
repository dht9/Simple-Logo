package model.commandBuilder;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.NumberCommand;
import model.commands.Product;
import model.commands.Sum;

public class DifferenceBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Sum(new Product(new NumberCommand(-1), dispenser.getNextCommand()), dispenser.getNextCommand());
	}

}
