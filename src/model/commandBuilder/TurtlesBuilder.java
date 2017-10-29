package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.Turtles;

public class TurtlesBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new Turtles();
	}

}
