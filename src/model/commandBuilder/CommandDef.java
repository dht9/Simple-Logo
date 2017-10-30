package model.commandBuilder;

import java.util.ArrayList;
import java.util.List;

import model.Command;
import model.CommandBuilder;
import model.SLogoException;
import model.TokenDispenser;
import model.commands.CommandList;
import model.commands.UserDefinedCommand;

public class CommandDef implements CommandBuilder{
	private List<String> variables;
	private List<Command> commands;
	private String name;

	public CommandDef(String name, List<String> variables, List<Command> commands) {
		this.name = name;
		this.variables = variables;
		this.commands = commands;
	}

	public Command build(TokenDispenser dispenser) throws SLogoException {
		List<Command> localVars = new ArrayList<>();
		for(int i = 0; i < variables.size(); i++)
			localVars.add(dispenser.getNextCommand());
		return new UserDefinedCommand(name, localVars);
	}

	public List<String> getParameterNames() {
		return variables;
	}

	public Command getCommandList() {
		System.out.println(commands);
		return new CommandList(commands);
	}
}
