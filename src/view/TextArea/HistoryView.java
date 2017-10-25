package view.TextArea;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import view.API.SubcomponentViewAPI;

public class HistoryView implements SubcomponentViewAPI {

	TextArea ta;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	public HistoryView(double height) {

		ta = new TextArea();
		ta.setMinHeight(height);
		ta.setWrapText(true);
		ta.setEditable(false);
		ta.appendText(myResources.getString("HistoryView") + "\n");
	}

	public void updateHistory(String newCode) {
		ta.appendText("\n" + newCode);
	}

	public Parent getParent() {
		return ta;
	}

}
