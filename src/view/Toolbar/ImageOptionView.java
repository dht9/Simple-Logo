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
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.API.CommandIOAPI.TurtleImageListener;
import view.API.ToolbarAPI.BackgroundOptionListener;
import view.API.ToolbarAPI.ImageOptionDisplay;

/**
 * Class that allows users to select a canvas color from a choice box.
 * 
 * @author DavidTran
 *
 */
public class ImageOptionView implements ImageOptionDisplay {

	private VBox optionView;
	private Label prompt;
	private ChoiceBox<String> cb;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private TurtleImageListener listener;
	private List<String> imageNameList;

	public ImageOptionView() {

		optionView = new VBox();

		prompt = new Label(myResources.getString("TurtleImagePrompt"));

		imageNameList = new ArrayList<String>(new ArrayList<String>(
				Arrays.asList(myResources.getString("TurtleImages").replaceAll("\\s+", "").split(","))));
		
		cb = new ChoiceBox<String>();
		for (String imageName : imageNameList) {
			cb.getItems().add(imageNameList.indexOf(imageName) + ". " + imageName);
		}
		
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//System.out.println(newValue.intValue());
				listener.imageChange(newValue.intValue());

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
	public void addTurtleImageListener(TurtleImageListener l) {
		listener = l;

	}

}
