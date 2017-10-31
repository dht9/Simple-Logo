package view.Windows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.BiConsumer;

import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import model.SLogoException;
import view.ErrorWindow;

/**
 * Class allowing users to see defined functions.
 * 
 * @author DavidTran
 *
 */
public class UserDefinedCommandView implements StringListener {

	private Map<String, List<String>> userCommands;
	private ScrollPane sp;
	private VBox vb;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private BiConsumer<String, String> myConsumer;

	public UserDefinedCommandView(double width, double height, BiConsumer<String, String> consumer) {
		myConsumer = consumer;

		sp = new ScrollPane();
		vb = new VBox(new Label(myResources.getString("UserDefinedCommandView") + "\n\n"));

		vb.setMinWidth(width);
		vb.setMinHeight(height);
		vb.setId("var-VBox");
		sp.setMinHeight(height);
		sp.setContent(vb);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	}

	@Override
	public void changedMap(Set<String> refMap, Map<String, List<String>> userCs) {
		userCommands = userCs;
		vb.getChildren().removeAll(vb.getChildren());

		vb.getChildren().add(new Label(myResources.getString("UserDefinedCommandView") + "\n\n"));

		for (String key : userCommands.keySet()) {
			Hyperlink hp = new Hyperlink(key + "\n");
			hp.setOnAction(e -> prompt(key));
			vb.getChildren().add(hp);
		}
	}

	@Override
	public Parent getParent() {
		return sp;
	}

	private void prompt(String key) {
		int i = 0;
		List<String> params = userCommands.get(key);
		List<String> inputs = new ArrayList<>();

		try {
			while (i < params.size()) {

				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Enter Parameter Dialog");
				dialog.setHeaderText("Enter Parameter");
				dialog.setContentText("Please enter a value for \'" + params.get(i) + "\':");

				Optional<String> result = dialog.showAndWait();

				result.ifPresent(value -> inputs.add(value));
				i++;
			}
			StringBuilder sb = new StringBuilder();
			for (String s : inputs)
				sb.append(s + " ");
			myConsumer.accept(key, sb.toString());
		} catch (SLogoException e) {
			new ErrorWindow(e.getMessage());
		}

	}

}
