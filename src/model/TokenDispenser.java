package model;

import java.util.List;

/**
 * An interface implemented by Parser to hide some methods from CommandBuilders. 
 * @see Parser
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public interface TokenDispenser {
	
	/**
	 * @return The next token and remove it
	 */
	public String getNextToken() throws SLogoException;
	
	/**
	 * @return The next token if it is a valid variable name, otherwise throws an
	 *         exception
	 */
	public String getNextVariable() throws SLogoException;

	/**
	 * @return The next token (without removing it)
	 */
	public String peek() throws SLogoException;

	/**
	 * @return Parses and returns the next series of tokens as a Command.
	 */
	public Command getNextCommand() throws SLogoException;

	/**
	 * Returns the next tokens as a list of commands, assuming that the next token
	 * is a liststart and the commands up to the listend are valid. Otherwise,
	 * throws an exception.
	 */
	public List<Command> getNextCommandList() throws SLogoException;

	/**
	 * Returns the next tokens as a list of tokens, assuming that the next token is
	 * a liststart and there is a listend before the end of the code. Otherwise,
	 * throws an exception.
	 */
	public List<String> getNextTokenList() throws SLogoException;

	/**
	 * Returns the next tokens as a list of tokens, assuming that the next token is
	 * a liststart and there is a listend before the end of the code AND that each
	 * token within is a valid variable name. Otherwise, throws an exception.
	 */
	public List<String> getNextVariableList() throws SLogoException;

	/**
	 * Used to define a user command without giving it behavior -- useful for
	 * parsing recursion
	 */
	public void defineCommand(String name, List<String> vars);
}
