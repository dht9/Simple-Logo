package view.Toolbar;

import java.util.ResourceBundle;
import java.util.function.BiConsumer;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import view.SubcomponentViewAPI;

/**
 * Class for pen size slider.
 * 
 * @author DavidTran
 *
 */
public class PenSlider implements SubcomponentViewAPI {

	private static ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private static final String PROMPT = "PenThicknessPrompt";
	private static final double SLIDER_MAX = 10;
	private static final double SLIDER_MIN = 0;
	private Label prompt;
	private Slider sl;
	private VBox view;
	private BiConsumer<String, String> myCommandConsumer;

	public PenSlider(BiConsumer<String, String> commandConsumer) {
		myCommandConsumer = commandConsumer;

		view = new VBox();
		sl = makeSlider();
		prompt = new Label(myResources.getString(PROMPT));
		view.getChildren().addAll(prompt, sl);
		view.setAlignment(Pos.CENTER);

		sl.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				myCommandConsumer.accept("SetPenSize", Double.toString(new_val.doubleValue()));
			}
		});

	}

	@Override
	public Parent getParent() {
		return view;
	}

	private Slider makeSlider() {
		Slider ret = new Slider(SLIDER_MIN, SLIDER_MAX, 1);
		ret.setShowTickLabels(true);
		ret.setShowTickMarks(true);
		ret.setMajorTickUnit(2);
		ret.setMinorTickCount(1);
		ret.setSnapToTicks(true);
		return ret;
	}
}
