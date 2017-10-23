package model.commandBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Command;
import model.CommandDef;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.UserDefinedCommand;

public class CommandBuilder implements CommandDef{
	private List<String> variables;
	private List<Command> commands;

	public CommandBuilder(List<String> variables, List<Command> commands) {
		this.variables = variables;
		this.commands = commands;
	}

	public Command build(TokenDispenser dispenser) throws SLogoException {
		Map<String, Command> localVars = new HashMap<String, Command>();
		for(String var : variables) {
			localVars.put(var, dispenser.getNextCommand());
		}
		return new UserDefinedCommand(localVars, commands);
	}
}
