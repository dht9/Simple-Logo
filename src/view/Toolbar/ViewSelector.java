package view.Toolbar;

import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.SubcomponentViewAPI;

/**
 * Class that allows users to select specific windows to view
 * 
 * @author taekwhunchung
 *
 */

public class ViewSelector implements SubcomponentViewAPI {

	private static final int MIN_WIDTH = 240;
	
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private HashMap<String, String> newViews;
	private WindowObservable<String> activeViews;
	private static final String STYLESHEET = "/resources/view/view.css";

	private Stage myStage;
	private Scene myScene;
	private VBox myWindows;

	public ViewSelector(WindowObservable<String> initViews) {
		activeViews = initViews;
		newViews = new HashMap<String, String>();

		createWindow();
		createCheckBoxes();
		createButton();
	}

	public void run() {
		myStage.show();
	}

	private void createButton() {
		Button confirmButton = new Button(myResources.getString("ViewButton"));
		confirmButton.setId("toolbar-button");
		confirmButton.setOnAction(e -> closeWindow());
		myWindows.getChildren().add(confirmButton);
	}

	private void closeWindow() {
		myStage.hide();
	}

	private void createCheckBoxes() {
		String[] windows = myResources.getString("Windows").split(",");
		for (int i = 0; i < windows.length; i = i + 2) {
			newViews.put(windows[i], windows[i + 1]);
			myWindows.getChildren().addAll(newCheckBox(windows[i]));

		}
	}

	private CheckBox newCheckBox(String windowName) {
		CheckBox cb = new CheckBox(windowName);
		if (activeViews.contains(newViews.get(windowName))) {
			cb.setSelected(true);
		}
		cb.setOnAction(e -> viewChanged(cb));
		return cb;
	}

	private void viewChanged(CheckBox checkbox) {
		if (checkbox.isSelected()) {
			activeViews.add(newViews.get(checkbox.getText()));
		} else {
			activeViews.remove(newViews.get(checkbox.getText()));
		}
	}

	private void createWindow() {
		myStage = new Stage();
		myWindows = new VBox();
		
		
		myScene = new Scene(myWindows);
		myScene.getStylesheets().add(STYLESHEET);

		myStage.setTitle(myResources.getString("ViewSelectorTitle"));
		Label instruction = new Label(myResources.getString("ViewSelectorMessage"));
		myWindows.getChildren().add(instruction);
		myWindows.setPadding(new Insets(10, 10, 10, 10));
		myWindows.setSpacing(10);
		myWindows.setMinWidth(MIN_WIDTH);
		myStage.setScene(myScene);

	}

	@Override
	public Parent getParent() {
		return myWindows;
	}

}
