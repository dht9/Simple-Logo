package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.HideTurtle;

public class HideTurtleBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new HideTurtle();
	}

}
