package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class that opens an error window.
 * 
 * @author DavidTran
 *
 */
public class ErrorWindow {

	public ErrorWindow(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
