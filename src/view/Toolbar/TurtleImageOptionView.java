package view.Toolbar;

import java.util.List;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Class that allows users to select a turtle image from a choice box.
 * 
 * @author DavidTran
 *
 */
public class TurtleImageOptionView extends OptionView {

	private List<String> imageNameList;
	private static final String PROMPT = "TurtleImagePrompt";
	private BiConsumer<String, String> myCommandConsumer;

	public TurtleImageOptionView(List<String> imgList, BiConsumer<String, String> commandConsumer) {

		super(PROMPT);

		imageNameList = imgList;
		myCommandConsumer = commandConsumer;

		for (String imageName : imageNameList) {
			cb.getItems().add(imageNameList.indexOf(imageName) + ". " + imageName);
		}

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				myCommandConsumer.accept("SetShape", Integer.toString(newValue.intValue()));
			}
		});
	}

}
