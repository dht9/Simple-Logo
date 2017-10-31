package view.Toolbar;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.SubcomponentViewAPI;

/**
 * Super class for 
 * 
 * @author DavidTran
 *
 */
public class OptionView implements SubcomponentViewAPI {

	protected VBox optionView;
	protected Label prompt;
	protected ChoiceBox<String> cb;
	protected ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");

	public OptionView(String label) {
		
		optionView = new VBox();
		prompt = new Label(myResources.getString(label));
		cb = new ChoiceBox<String>();
		optionView.getChildren().addAll(prompt, cb);
		optionView.setAlignment(Pos.BASELINE_CENTER);
		
	}

	@Override
	public Parent getParent() {
		return optionView;
	}

}
