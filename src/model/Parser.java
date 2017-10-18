package model;

import java.util.Map;
import java.util.ResourceBundle;

public class Parser {

	private int index = 0;
	private String[] tokens;
	private Map<String, CommandDef> availableCommands;
	
	private static ResourceBundle syntax = ResourceBundle.getBundle("resources.languages/Syntax");
	
	public Parser(String code, Map<String, CommandDef> availableCommands) {
		code = code.replaceAll(syntax.getString("Comment"), " ");
		tokens = code.split("\\s+");
		this.availableCommands = availableCommands;
	}
	
	public Command getNextCommand() {
		CommandDef builder = availableCommands.get(tokens[index]);
		index++;
		Command[] parameters = new Command[builder.numExpected()];
		for(int start = index; index - start < parameters.length; index++) {
			parameters[index-start] = getNextCommand();
		}
		return builder.build(parameters);
	}
	
	public boolean hasNextCommand() {
		return index < tokens.length;
	}
}
