package controller;

import controller.API.DriverAPI;
import javafx.stage.Stage;
import view.View;

public class Driver implements DriverAPI {

	private View myView;
	
	/**
	 * Constructor
	 */
	public Driver(Stage stage) {
		myView = new View(stage);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
