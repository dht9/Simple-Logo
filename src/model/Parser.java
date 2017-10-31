package model;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import model.commandBuilder.CommandDef;
import model.commands.NumberCommand;
import model.commands.VariableCommand;

/**
 * Parses SLogo Code into executable Commands. Depends on CommandManager,
 * SLogoException, Command, CommandBuilder, and subclasses found in
 * model.commandBuilder, model.commands.
 * 
 * Typically used in a loop:
 * 
 * <pre>
 * <code>Parser p = new Parser("fd 100 right 90");
 * while(p.hasNextCommand()){
 * 	p.getNextCommand().execute(turtle, commands, variables); 
 * }
 * </code>
 * </pre>
 * 
 * Note that the CommandBuilders are responsible for getting their own
 * parameters.
 * 
 * A 'token' is a series of non-whitespace characters separated from other
 * tokens by whitespace.
 * 
 * @author Ian Eldridge-Allegra
 * 
 */
public class Parser implements TokenDispenser {

	private int index;
	private String[] tokens;
	private CommandManager availableCommands;

	public static final ResourceBundle SYNTAX = ResourceBundle.getBundle("resources.languages/Syntax");

	/**
	 * @param code
	 *            The segment of code to execute
	 * @param availableCommands
	 *            The known commands currently available
	 */
	public Parser(String code, CommandManager availableCommands) {
		index = 0;
		code = code.replaceAll(SYNTAX.getString("Comment"), " ").toLowerCase();
		tokens = code.split(SYNTAX.getString("Whitespace"));
		this.availableCommands = availableCommands;
	}

	/**
	 * @return The next token (without removing it)
	 */
	@Override
	public String peek() throws SLogoException {
		if (!hasNextCommand())
			throw new SLogoException("EOF");
		return tokens[index];
	}

	/**
	 * @return The next token and remove it
	 */
	@Override
	public String getNextToken() throws SLogoException {
		if (!hasNextCommand())
			throw new SLogoException("EOF");
		index++;
		return tokens[index - 1];
	}

	/**
	 * @return The next token if it is a valid variable name, otherwise throws an
	 *         exception
	 */
	@Override
	public String getNextVariable() throws SLogoException {
		String name = getNextToken();
		if (!name.matches(SYNTAX.getString("Variable")))
			throw new SLogoException("InvalidVar", name);
		return name;
	}

	/**
	 * @return Whether there is more code to parse
	 */
	public boolean hasNextCommand() {
		return index < tokens.length && tokens[index].length() > 0;
	}

	/**
	 * @return Parses and returns the next series of tokens as a Command.
	 */
	@Override
	public Command getNextCommand() throws SLogoException {
		String token = getNextToken();

		if (token.matches(SYNTAX.getString("GroupStart")))
			return generateGroup();
		if (token.matches(SYNTAX.getString("Constant")))
			return new NumberCommand(Double.parseDouble(token));
		if (token.matches(SYNTAX.getString("Variable")))
			return new VariableCommand(token);
		if (token.matches(SYNTAX.getString("Command")))
			return availableCommands.get(token).build(this);
		throw new SLogoException("UnexpectedToken", token);
	}

	private Command generateGroup() {
		String token = getNextToken();
		Command toReturn = availableCommands.get(token).buildGroup(this);
		getNextToken();
		return toReturn;
	}

	/**
	 * Returns the next tokens as a list of commands, assuming that the next token
	 * is a liststart and the commands up to the listend are valid. Otherwise,
	 * throws an exception.
	 */
	@Override
	public List<Command> getNextCommandList() throws SLogoException {
		return getNextList(() -> getNextCommand());
	}

	/**
	 * Returns the next tokens as a list of tokens, assuming that the next token is
	 * a liststart and there is a listend before the end of the code. Otherwise,
	 * throws an exception.
	 */
	@Override
	public List<String> getNextTokenList() throws SLogoException {
		return getNextList(() -> getNextToken());
	}

	/**
	 * Returns the next tokens as a list of tokens, assuming that the next token is
	 * a liststart and there is a listend before the end of the code AND that each
	 * token within is a valid variable name. Otherwise, throws an exception.
	 */
	@Override
	public List<String> getNextVariableList() throws SLogoException {
		return getNextList(() -> getNextVariable());
	}

	private <T> List<T> getNextList(Supplier<T> supplier) throws SLogoException {
		String token = getNextToken();
		if (!token.matches(SYNTAX.getString("ListStart")))
			throw new SLogoException("ExpectedList", token);

		List<T> result = new ArrayList<T>();
		while (!peek().matches(SYNTAX.getString("ListEnd"))) {
			result.add(supplier.get());
		}
		getNextToken();
		return result;
	}

	/**
	 * Used to define a user command without giving it behavior -- useful for
	 * parsing recursion
	 */
	@Override
	public void defineCommand(String name, List<String> vars) {
		availableCommands.put(name, new CommandDef(name, vars, new ArrayList<Command>()));
	}
}
