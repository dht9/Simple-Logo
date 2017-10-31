package model.commandBuilder;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.SetShape;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetShapeBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		return new SetShape(dispenser.getNextCommand());
	}

}
