package view.Toolbar;

import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.SubcomponentViewAPI;

/**
 * Class for pen up/down buttons.
 * 
 * @author DavidTran
 *
 */
public class PenButtons implements SubcomponentViewAPI {

	private VBox view;
	private Button upButton;
	private Button downButton;
	private static final ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private static final String PROMPT = myResources.getString("PenButtonsPrompt");
	private BiConsumer<String, String> myCommandConsumer;

	public PenButtons(BiConsumer<String, String> commandConsumer) {
		view = new VBox();
		myCommandConsumer = commandConsumer;
		Label prompt = new Label(PROMPT);
		HBox buttonPanel = new HBox();
		upButton = makeButton(myResources.getString("UpButtonCommand"), e -> myCommandConsumer.accept("PenUp", ""));
		downButton = makeButton(myResources.getString("DownButtonCommand"),
				e -> myCommandConsumer.accept("PenDown", ""));
		buttonPanel.getChildren().addAll(upButton, downButton);
		view.getChildren().addAll(prompt, buttonPanel);
		view.setAlignment(Pos.BASELINE_CENTER);
	}

	@Override
	public Parent getParent() {
		return view;
	}

	private Button makeButton(String label, EventHandler<ActionEvent> e) {
		Button ret = new Button(label);
		ret.setOnAction(e);
		ret.setId("toolbar-button");
		return ret;
	}
}
