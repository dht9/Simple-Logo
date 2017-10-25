package view.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.API.CommandIOAPI.TurtleListener;
import view.API.ToolbarAPI.BackgroundOptionListener;
import view.API.ToolbarAPI.PenOptionDisplay;
import view.API.ToolbarAPI.PenOptionListener;

/**
 * Class that allows users to select a pen color from a choice box.
 * 
 * @author taekwhunchung
 *
 */
public class PenOptionView implements PenOptionDisplay {

	private VBox optionView;
	private Label prompt;
	private ChoiceBox<String> cb;
	private List<String> colorList;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private TurtleListener listener;

	public PenOptionView() {

		optionView = new VBox();

		prompt = new Label(myResources.getString("PenPrompt"));

		colorList = new ArrayList<String>(
				Arrays.asList(myResources.getString("PenColors").replaceAll("\\s+", "").split(",")));

		cb = new ChoiceBox<String>();
		
		for (String color : colorList)
			cb.getItems().add(colorList.indexOf(color) + ". " + color);
		
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				listener.penColorChange(newValue.intValue());
			}
		});

		optionView.getChildren().addAll(prompt, cb);
		optionView.setAlignment(Pos.CENTER);
	}

	@Override
	public Parent getParent() {
		return optionView;
	}

	@Override
	public void addPenOptionListener(TurtleListener l) {
		listener = l;
	}

}
