package model.commandBuilder;

import java.util.List;

import model.Command;
import model.CommandDef;
import model.Parser;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.For;

public class ForBuilder implements CommandDef {

	@Override
	public Command build(TokenDispenser dispenser) throws SLogoException {
		String openBracket = dispenser.getNextToken();
		if (!openBracket.matches(Parser.SYNTAX.getString("ListStart")))
			throw new SLogoException("UnexpectedToken", openBracket);
		String variable = dispenser.getNextVariable();
		Command start = dispenser.getNextCommand();
		Command end = dispenser.getNextCommand();
		Command increment = dispenser.getNextCommand();
		String closeBracket = dispenser.getNextToken();
		if (!closeBracket.matches(Parser.SYNTAX.getString("ListEnd")))
			throw new SLogoException("UnexpectedToken", openBracket);
		List<Command> commandList = dispenser.getNextCommandList();
		return new For(variable, start, end, increment, commandList);
	}

}
