package view;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.API.BackgroundOptionDisplay;
import view.API.BackgroundOptionListener;

/**
 * Class that allows users to select a canvas color from a choice box.
 * 
 * @author DavidTran
 *
 */
public class BackgroundOptionView implements BackgroundOptionDisplay {

	private VBox optionView;
	private Label prompt;
	private ChoiceBox<String> cb;
	private List<String> colorList;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private BackgroundOptionListener listener;

	public BackgroundOptionView() {

		optionView = new VBox();
		
		prompt = new Label(myResources.getString("BackgroundPrompt"));
		
		colorList = new ArrayList<String>(
				Arrays.asList(myResources.getString("BackgroundColors").replaceAll("\\s+", "").split(",")));

		cb = new ChoiceBox<String>(FXCollections.observableArrayList(colorList));
		cb.setTooltip(new Tooltip("Select the background color"));
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(newValue.intValue());
				listener.backgroundColorChange(Color.valueOf((colorList.get(newValue.intValue()))));

			}
		});
		
		cb.setId("background_cb");
		
		optionView.getChildren().addAll(prompt, cb);
		optionView.setAlignment(Pos.CENTER);
	}

	@Override
	public Parent getParent() {
		return optionView;
	}

	@Override
	public void addBackgroundOptionListener(BackgroundOptionListener l) {
		listener = l;

	}

}
