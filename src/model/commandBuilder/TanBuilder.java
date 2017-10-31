package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.NumberCommand;
import model.commands.Pow;
import model.commands.Product;
import model.commands.Sin;
import model.commands.Sum;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class TanBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		Command degrees = dispenser.getNextCommand();
		return new Product(new Sin(degrees), new Pow(new Sin(new Sum(degrees, new NumberCommand(90))) , new NumberCommand(-1)));
	}

}
