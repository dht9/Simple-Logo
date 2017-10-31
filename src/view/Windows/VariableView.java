package view.Windows;

import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import view.ErrorWindow;
import view.SubcomponentViewAPI;

/**
 * Class allowing users to see values of current variables
 * 
 * @author DavidTran
 *
 */
public class VariableView implements SubcomponentViewAPI, VariableListener {

	private static final ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private Map<String, Double> variableMap;
	private VBox view;
	private VBox varsBox;
	private static final String HEADER = myResources.getString("VariableView") + "\n\n";
	private Label header;

	public VariableView(double height) {
		view = new VBox();
		header = new Label(HEADER);
		
		varsBox = new VBox();
		varsBox.setMinHeight(height);
		varsBox.getChildren().add(header);
		varsBox.setId("var-VBox");
		
		ScrollPane sp = (ScrollPane) new ScrollPaneView().getParent();
		sp.setMinHeight(height);
		sp.setContent(varsBox);
		
		view.getChildren().addAll(sp);
		view.setAlignment(Pos.CENTER);
	}

	/*************************** PUBLIC METHODS ********************************/

	@Override
	public void changedMap(Map<String, Double> vars) {
		variableMap = vars;
		//System.out.println("changed");
		varsBox.getChildren().removeAll(varsBox.getChildren());
		varsBox.getChildren().add(header);

		for (String key : variableMap.keySet()) {
			Hyperlink entry = new Hyperlink(key + " : " + variableMap.get(key));
			entry.setOnMouseClicked(e -> updatePrompt(key));
			varsBox.getChildren().add(entry);
		}
	}

	/*************************** PRIVATE METHODS ********************************/

	private void updatePrompt(String key) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Update Variable Dialog");
		dialog.setHeaderText("Update Variable");
		dialog.setContentText("Please enter new variable value for \'" + key + "\':");

		Optional<String> result = dialog.showAndWait();
		try {
			result.ifPresent(value -> variableMap.put(key,Double.parseDouble(value)));
		} catch (NumberFormatException e) {
			new ErrorWindow(e.getMessage());
		}
		changedMap(variableMap);
	}

	@Override
	public Parent getParent() {
		return view;
	}
}
