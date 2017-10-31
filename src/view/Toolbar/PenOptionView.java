package view.Toolbar;

import java.util.List;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Class that allows users to select a pen color from a choice box.
 * 
 * @author taekwhunchung
 *
 */
public class PenOptionView extends OptionView {

	private static final String PROMPT = "PenPrompt";
	private BiConsumer<String, String> myCommandConsumer;

	public PenOptionView(List<String> colorList, BiConsumer<String, String> commandConsumer) {
		super(PROMPT);
		myCommandConsumer = commandConsumer;
		makeChoiceBox(colorList);
	}

	/**
	 * method that creates default ChoiceBoxes. Also changes ChoiceBox when user
	 * defines new Color.
	 * 
	 * @param colorList
	 */

	public void makeChoiceBox(List<String> colorList) {
		cb.getItems().removeAll(cb.getItems());

		for (String color : colorList)
			cb.getItems().add(colorList.indexOf(color) + ". " + color);

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myCommandConsumer.accept("SetPenColor", Integer.toString(newValue.intValue()));
			}
		});

		cb.setMinWidth(cb.getWidth());
	}

}
