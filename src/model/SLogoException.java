package model;

import java.util.ResourceBundle;

/**
 * A RuntimeException mainly intended for parsing problems encountered when
 * parsing commands.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public class SLogoException extends RuntimeException {
	private static final long serialVersionUID = -372878051697616743L;
	private static final ResourceBundle ERROR_BUNDLE = ResourceBundle.getBundle("resources.errors.SyntaxErrors");

	/**
	 * Assumes that the key passed is valid, relies on properties file.
	 * 
	 * @param key To the message in the syntaxerrors bundle.
	 * @param objects Used for String.format
	 */
	public SLogoException(String key, Object... objects) {
		super(String.format(ERROR_BUNDLE.getString(key), objects));
	}

	public SLogoException() {
		super("This is the default SLogoException message -- use constructor "
				+ "SLogoException(s, objects) unless planning to change the message elsewhere");
	}
}
