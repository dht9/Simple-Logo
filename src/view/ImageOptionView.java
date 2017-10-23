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
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.API.BackgroundOptionListener;
import view.API.ImageOptionDisplay;
import view.API.TurtleImageListener;

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
	private List<Image> imageList;

	public ImageOptionView() {

		optionView = new VBox();

		prompt = new Label(myResources.getString("TurtleImagePrompt"));

		imageNameList = new ArrayList<String>(new ArrayList<String>(
				Arrays.asList(myResources.getString("TurtleImages").replaceAll("\\s+", "").split(","))));
		imageList = new ArrayList<Image>();
		
		for (String s : imageNameList) {
			Image image = new Image(
					getClass().getClassLoader().getResourceAsStream("resources/images/" + s));
			imageList.add(image);
		}

		cb = new ChoiceBox<String>(FXCollections.observableArrayList(imageNameList));
		cb.setTooltip(new Tooltip("Select the turtle image"));
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(newValue.intValue());
				listener.imageChange(imageList.get(newValue.intValue()));

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
	public void addTurtleImageListener(TurtleImageListener l) {
		listener = l;

	}

}
