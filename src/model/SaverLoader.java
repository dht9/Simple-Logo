package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * A class providing static methods to serialize/deserialize objects to/from
 * files. References are noted in method comments.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public class SaverLoader {
	private static final String KNOWN_FILES = "src/resources/data/KnownFiles.properties";

	/**
	 * Modified from https://www.tutorialspoint.com/java/java_serialization.htm
	 * Saves a serializable object to the given file
	 */
	public static void save(Serializable object, String file) {
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(object);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			throw new SLogoException("SaveFail", file);
		}
	}

	/**
	 * @param file
	 *            To load from
	 * @return the object stored in the file
	 */
	public static Object load(String file) {
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object result = in.readObject();
			in.close();
			fileIn.close();
			return result;
		} catch (IOException | ClassNotFoundException exc) {
			throw new SLogoException("LoadFail", file);
		}
	}

	/**
	 * Modified from
	 * https://stackoverflow.com/questions/22370051/how-to-write-values-in-a-properties-file-through-java-code
	 * Updates a specific properties file to include file in its keyset.
	 */
	public static void addToKnown(String file) {
		try {
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(KNOWN_FILES);
			prop.load(in);
			in.close();
			prop.put(file, "Added by Program");
			FileOutputStream out = new FileOutputStream(KNOWN_FILES);
			prop.store(out, null);
			out.close();
		} catch (IOException e) {
			throw new SLogoException("KnownFiles");
		}
	}

	/**
	 * @return The known files, drawn from the properties file being written by
	 *         addToKnown(String file)
	 */
	public static Enumeration<String> knownFiles() {
		return ResourceBundle.getBundle(KNOWN_FILES).getKeys();
	}
}
