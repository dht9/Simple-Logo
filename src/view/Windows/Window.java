package view.Windows;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import view.SubcomponentViewAPI;

/**
 * Super class for sub-windows displayed on side scroll panes.
 * 
 * @author DavidTran
 *
 */
public class Window implements SubcomponentViewAPI {

	protected TextArea ta;
	protected VBox view;
	protected ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	public Window(double height) {
		view = new VBox();
		view.setMinHeight(height);
		ta = createTA(height);
		view.getChildren().addAll(ta);
	}

	/*************************** PRIVATE METHODS ********************************/

	private TextArea createTA(double height) {
		TextArea ret = new TextArea();
		ret.setMinHeight(height);
		ret.setWrapText(true);
		ret.setEditable(false);
		return ret;
	}

	/*************************** PUBLIC METHODS ********************************/

	@Override
	public Parent getParent() {
		return view;
	}
}
