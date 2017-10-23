package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import view.API.StringListener;

public class CommandManager {
	public static final String PATH_START = "resources.languages.";
	public static final String DEFAULT_LANGUAGE = "English";

	private String builderPropertiesPath;

	private Map<String, CommandDef> builtInCommands = new HashMap<>();
	private Map<String, CommandDef> userCommands = new HashMap<>();
	private List<StringListener> listeners = new ArrayList<>();

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
			CommandDef definition;
			try {
				definition = (CommandDef) Class.forName(classFile.getString(command)).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException("Check basicCommands.properties -- key: " + command);
			}

			builtInCommands.put(acceptedCommands.getString(command), definition);
		}
	}

	public void setLanguage(String language) {
		builtInCommands.clear();
		loadCommands(builderPropertiesPath, language);
		updateListeners();
	}

	public void clear() {
		builtInCommands.clear();
		userCommands.clear();
		updateListeners();
	}

	public CommandDef get(String s) throws SLogoException {
		for (String regex : builtInCommands.keySet()) {
			if (s.matches(regex))
				return builtInCommands.get(regex);
		}
		if (userCommands.containsKey(s))
			return userCommands.get(s);
		throw new SLogoException("UnexpectedCommand", s);
	}

	public void addListener(StringListener commandListener) {
		listeners.add(commandListener);
		updateListeners();
	}

	public void put(String name, CommandDef definition) {
		builtInCommands.put(name, definition);
		userCommands.put(name, definition);
		updateListeners();
	}

	public boolean checkIfBuiltIn(String name) {
		// System.out.println(builtInCommands.keySet().toString());
		for (String regex : builtInCommands.keySet()) {
			if (name.matches(regex))
				return true;
		}
		return false;
	}

	private void updateListeners() {
		for (StringListener listener : listeners)
			listener.changedMap(Collections.unmodifiableSet(builtInCommands.keySet()),
					Collections.unmodifiableSet(userCommands.keySet()));
	}
}
