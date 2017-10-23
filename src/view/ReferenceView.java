package view;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import model.CommandDef;
import view.API.StringListener;

/**
 * A Pane representing the basic instructions for using SLogo.
 * 
 * @author DavidTran
 *
 */
public class ReferenceView implements StringListener {

	TextArea ta;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	public ReferenceView(double height) {

		ta = new TextArea();
		ta.setMinHeight(height);
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText(myResources.getString("ReferenceView"));
	}

	@Override
	public void changedMap(Set<String> refMap, Set<String> commandSet) {
		ta.clear();
		ta.appendText(myResources.getString("ReferenceView") + "\n\n");
		for (String key : refMap) {
			ta.appendText(key + "\n");// + " : " + newMap.get(key));
		}
		ta.setScrollTop(0);

	}

	@Override
	public Parent getParent() {
		return ta;
	}

}
