package view.Toolbar;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Class that stores active windows (windows that users can view) in ArrayList
 * as string.
 * 
 * Extends Observable, so any class that implements Observer can detect change
 * in this class.
 * 
 * @author taekwhunchung
 *
 */

public class WindowObservable<T> extends Observable {

	private ArrayList<String> windows;

	/**
	 * 
	 * Constructor for Observable ArrayList of active windows as Strings
	 * 
	 * @param activeWindows
	 */

	public WindowObservable(ArrayList<String> activeWindows) {
		windows = activeWindows;
	}

	/**
	 * 
	 * method to add selected window to active windows and notify observers.
	 * 
	 * @param t
	 */
	public void add(T t) {
		String str = (String) t;
		windows.add(str);
		this.setChanged();
		notifyObservers(t);
	}

	/**
	 * 
	 * method to remove selected window from active windows and notify observers.
	 * 
	 * @param t
	 */

	public void remove(T t) {
		String str = (String) t;
		windows.remove(str);
		this.setChanged();
		notifyObservers(t);
	}

	/**
	 * 
	 * method that returns true if active windows contains window (param)
	 * 
	 * @param String
	 *            name of window
	 * @return boolean
	 */

	public boolean contains(T t) {
		return windows.contains(t);
	}
	
	/**
	 * method that returns windows
	 * 
	 * @return ArrayList<String> windows
	 */

	public ArrayList<String> getList() {
		return windows;
	}

}
