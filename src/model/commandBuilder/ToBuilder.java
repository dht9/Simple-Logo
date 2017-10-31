package model.commandBuilder;

import java.util.List;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.To;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class ToBuilder implements CommandBuilder {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		String name = dispenser.getNextToken();
		List<String> vars = dispenser.getNextVariableList();
		dispenser.defineCommand(name, vars);
		return new To(name, vars, dispenser.getNextCommandList());
	}

}
