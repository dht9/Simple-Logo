package view.TextArea;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import model.CommandDef;
import view.API.TextAreaAPI.UserDefinedCommandDisplay;

/**
 * Class allowing users to see defined functions.
 * 
 * @author DavidTran
 *
 */
public class UserDefinedCommandView implements UserDefinedCommandDisplay {

	TextArea ta;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	public UserDefinedCommandView(double height) {

		ta = new TextArea();
		ta.setMinHeight(height);
		ta.setWrapText(true);
		ta.setEditable(false);

		ta.appendText(myResources.getString("UserDefinedCommandView"));
	}

	@Override
	public void changedMap(Set<String> refMap, Set<String> commandSet) {
		ta.clear();
		ta.appendText(myResources.getString("UserDefinedCommandView") + "\n\n");
		for (String key : commandSet) {
			ta.appendText(key + "\n");
		}

	}

	@Override
	public Parent getParent() {
		return ta;
	}

	@Override
	public void clearCommands() {
		// TODO Auto-generated method stub

	}

}
