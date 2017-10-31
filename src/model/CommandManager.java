package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import model.commandBuilder.CommandDef;
import view.Windows.StringListener;

/**
 * Holds the CommandBuilders used for parsing, including user-defined commands.
 * Encapsulates loading languages, updating listeners, and saving/loading
 * commands.
 * 
 * Assumes most data passed to it is checked -- languages/files must be valid,
 * objects not null, etc.
 * 
 * Depends on CommandBuilder, CommandDef, and SaverLoader.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public class CommandManager {
	public static final String PATH_START = "resources.languages.";
	public static final String DEFAULT_LANGUAGE = "English";

	private String builderPropertiesPath;

	private Map<String, CommandBuilder> builtInCommands = new HashMap<>();
	private HashMap<String, CommandDef> userCommands = new HashMap<>();
	private List<StringListener> listeners = new ArrayList<>();

	/**
	 * @param builderPropertiesPath
	 *            The valid path to a properties file containing builder classes.
	 */
	public CommandManager(String builderPropertiesPath) {
		this.builderPropertiesPath = builderPropertiesPath;
		setLanguage(DEFAULT_LANGUAGE);
	}

	private void loadCommands(String propertyFile, String language) {
		ResourceBundle classFile = ResourceBundle.getBundle(propertyFile);
		ResourceBundle acceptedCommands = ResourceBundle.getBundle(PATH_START + language);
		Enumeration<String> keys = classFile.getKeys();
		while (keys.hasMoreElements()) {
			String command = keys.nextElement();
			CommandBuilder definition;
			try {
				definition = (CommandBuilder) Class.forName(classFile.getString(command)).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Check basicCommands.properties -- key: " + command);
			}

			builtInCommands.put(acceptedCommands.getString(command), definition);
		}
	}

	/**
	 * Changes the known commands into the language specified.
	 * 
	 * @param language
	 *            The language -- must have a matching properties file.
	 */
	public void setLanguage(String language) {
		builtInCommands.clear();
		loadCommands(builderPropertiesPath, language);
		updateListeners();
	}

	/**
	 *  Removes all commands, user created or otherwise
	 */
	public void clear() {
		builtInCommands.clear();
		userCommands.clear();
		updateListeners();
	}

	/**
	 * @param s
	 *            The command name
	 * @return The CommandBuilder associated with the name given
	 * @throws SLogoException
	 *             If command isn't known
	 */
	public CommandBuilder get(String s) throws SLogoException {
		for (String regex : builtInCommands.keySet()) {
			if (s.matches(regex))
				return builtInCommands.get(regex);
		}
		return getUserDefinedCommand(s);
	}

	/**
	 * @param s
	 *            The command name
	 * @return The CommandDef associated with the name
	 * @throws SLogoException
	 *             If command isn't known
	 */
	public CommandDef getUserDefinedCommand(String s) throws SLogoException {
		if (userCommands.containsKey(s))
			return userCommands.get(s);
		throw new SLogoException("UnexpectedCommand", s);
	}

	/**
	 * @param commandListener
	 *            The new listener that should be notified about changes to commands
	 */
	public void addListener(StringListener commandListener) {
		listeners.add(commandListener);
		updateListeners();
	}

	/**
	 * Adds or replaces a user-created command
	 * 
	 * @param name
	 *            The new/changed command's name
	 * @param definition
	 *            Represents the new command
	 */
	public void put(String name, CommandDef definition) {
		userCommands.put(name, definition);
		updateListeners();
	}

	/**
	 * @param name
	 *            The command name
	 * @return true if the command matches a built-in command (ie excludes
	 *         user-created ones)
	 */
	public boolean checkIfBuiltIn(String name) {
		for (String regex : builtInCommands.keySet()) {
			if (name.matches(regex))
				return true;
		}
		return false;
	}

	/**
	 * Updates listeners to a change in the commands
	 */
	private void updateListeners() {
		Map<String, List<String>> userCs = new HashMap<String, List<String>>();
		for(String name : userCommands.keySet()) {
			userCs.put(name, Collections.unmodifiableList(userCommands.get(name).getParameterNames()));
		}
		for (StringListener listener : listeners)
			listener.changedMap(Collections.unmodifiableSet(builtInCommands.keySet()),
					userCs);
	}

	/**
	 * Saves the user commands to a file
	 * @param fileName Path to which this should be saved
	 */
	public void save(String fileName) {
		SaverLoader.save(userCommands, fileName);
	}

	/**
	 * Loads user commands from a file
	 * @param fileName Path of where to load commands from
	 */
	@SuppressWarnings("unchecked")
	public void load(String fileName) {
		HashMap<String, CommandDef> data = (HashMap<String, CommandDef>) SaverLoader.load(fileName);
		userCommands = new HashMap<>();
		for (String s : data.keySet()) {
			userCommands.put(s, data.get(s));
		}
		
		System.out.println(userCommands);
		updateListeners();
	}
}