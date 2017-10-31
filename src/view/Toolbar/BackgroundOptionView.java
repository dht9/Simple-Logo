package view.Toolbar;

import java.util.List;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Class that allows users to select a canvas color from a choice box.
 * 
 * @author DavidTran
 *
 */
public class BackgroundOptionView extends OptionView {

	private static final String PROMPT = "BackgroundPrompt";
	private BiConsumer<String, String> myCommandConsumer;

	public BackgroundOptionView(List<String> colorList, BiConsumer<String, String> commandConsumer) {
		super(PROMPT);
		myCommandConsumer = commandConsumer;
		makeChoiceBox(colorList);
	}

	/**
	 * Called when a choice-box needs to be made/updated.
	 * 
	 * @param list
	 *            - colorList to display choice-box options
	 */
	public void makeChoiceBox(List<String> colorList) {

		cb.getItems().removeAll(cb.getItems());

		for (String color : colorList)
			cb.getItems().add(colorList.indexOf(color) + ". " + color);

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myCommandConsumer.accept("SetBackground", Integer.toString(newValue.intValue()));
			}
		});

		cb.setMinWidth(cb.getWidth());
	}
}
