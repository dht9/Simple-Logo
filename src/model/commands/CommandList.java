package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Parser;
import model.Turtle;

public class CommandList implements Command {	
	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		return 0; //TODO
	}
}
