package model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import model.commands.NumberCommand;
import model.commands.VariableCommand;

public class Parser implements TokenDispenser{

	private int index;
	private String[] tokens;
	private CommandManager availableCommands;
	
	public static final ResourceBundle SYNTAX = ResourceBundle.getBundle("resources.languages/Syntax");
	
	public Parser(String code, CommandManager availableCommands) {
		index = 0;
		code = code.replaceAll(SYNTAX.getString("Comment"), " ").toLowerCase();
		tokens = code.split("\\s+");
		this.availableCommands = availableCommands;
	}
	
	public String peek() throws SLogoException {
		if(!hasNextCommand())
			throw new SLogoException("EOF");
		return tokens[index];
	}
	
	public String getNextToken() throws SLogoException {
		if(!hasNextCommand())
			throw new SLogoException("EOF");
		index++;
		return tokens[index-1];
	}
	
	public String getNextVariable() throws SLogoException {
		String name = getNextToken();
		if(!name.matches(SYNTAX.getString("Variable")))
			throw new SLogoException("InvalidVar", name);
		return name;
	}
	
	public boolean hasNextCommand() {
		return index < tokens.length && tokens[index].length()>0;
	}
	
	public Command getNextCommand() throws SLogoException {
		String token = getNextToken();
		
		if(token.matches(SYNTAX.getString("Constant")))
			return new NumberCommand(Double.parseDouble(token));
		if(token.matches(SYNTAX.getString("Variable")))
			return new VariableCommand(token);
		if(token.matches(SYNTAX.getString("Command")))
			return availableCommands.get(token).build(this);
		throw new SLogoException("UnexpectedToken", token);
	}

	@Override
	public List<Command> getNextCommandList() throws SLogoException {
		return getNextList(()->getNextCommand());
	}

	@Override
	public List<String> getNextTokenList() throws SLogoException {
		return getNextList(()->getNextToken());
	}
	
	@Override
	public List<String> getNextVariableList() throws SLogoException {
		return getNextList(()->getNextVariable());
	}

	
	private <T> List<T> getNextList(Supplier<T> supplier) throws SLogoException{
		String token = getNextToken();
		if(!token.matches(SYNTAX.getString("ListStart")))
			throw new SLogoException("ExpectedList", token); 
		
		List<T> result = new ArrayList<T>();
		while(!peek().matches(SYNTAX.getString("ListEnd"))) {
			result.add(supplier.get());
		}
		getNextToken();
		return result;
	}
}
