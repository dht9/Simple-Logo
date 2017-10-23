package model;

import java.util.ResourceBundle;

public class SLogoException extends RuntimeException {
	/**
	 * Default ID
	 */
	private static final long serialVersionUID = 1L;
	
	private static final ResourceBundle ERROR_BUNDLE = ResourceBundle.getBundle("resources.errors.SyntaxErrors");
	
	public SLogoException(String key, Object... objects) {
		super(String.format(ERROR_BUNDLE.getString(key), objects));
	}
}
