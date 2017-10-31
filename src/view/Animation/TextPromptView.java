package view.Animation;

import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.SLogoException;
import view.ErrorWindow;
import view.Toolbar.LanguageListener;

/**
 * Class allowing users to enter and clear text/key commands.
 * 
 * @author DavidTran
 *
 */
public class TextPromptView extends HBox implements TextPromptAPI, LanguageListener {
	private TextArea tp;
	private Button runButton;
	private Button clearButton;
	private VBox buttonPanel;
	private static final String DEFAULT_LANGUAGE = "English";

	private Consumer<String> commandConsumer;
	private Consumer<String> historyConsumer;
	private Runnable undoneClearer;
	private ResourceBundle acceptedCommands;

	public TextPromptView(double width, double height, Consumer<String> commandConsumer,
			Consumer<String> historyConsumer, Runnable undoneClearer) {

		this.setMaxWidth(width);
		this.setMaxHeight(height);

		tp = new TextArea();
		tp.setPrefWidth(width * .75);
		tp.setPrefHeight(height);
		tp.setId("text-prompt");
		this.getChildren().add(tp);

		this.commandConsumer = commandConsumer;
		this.historyConsumer = historyConsumer;
		this.undoneClearer = undoneClearer;
		acceptedCommands = ResourceBundle.getBundle("resources.languages." + DEFAULT_LANGUAGE);

		addButtons(width - tp.getPrefWidth(), height / 2);

	}

	/*************************** PUBLIC METHODS ********************************/

	@Override
	public void runCommand(String s, String params) {

		if (acceptedCommands.containsKey(s)) {
			String command = acceptedCommands.getString(s).split("\\|")[0] + " " + params;
			commandConsumer.accept(command);
		} else {
			String command = s + " " + params;
			commandConsumer.accept(command);
		}

	}

	@Override
	public void languageChange(String language) {
		acceptedCommands = ResourceBundle.getBundle("resources.languages." + language);
	}

	@Override
	public void handleInput(KeyCode code) {
		switch (code) {
		case W:
			this.runCommand("Forward", "1");
			break;
		case S:
			this.runCommand("Backward", "1");
			break;
		case A:
			this.runCommand("Left", "90");
			this.runCommand("Forward", "1");
			this.runCommand("Right", "90");
			break;
		case D:
			this.runCommand("Right", "90");
			this.runCommand("Forward", "1");
			this.runCommand("Left", "90");
			break;
		case R:
			this.runCommand("Left", "1");
			break;
		case T:
			this.runCommand("Right", "1");
			break;
		default:
			break;
		}
	}

	/*************************** PRIVATE METHODS ********************************/

	private void addButtons(double width, double height) {
		buttonPanel = new VBox();

		runButton = makeButton("Run", e -> {
			enter();
			undoneClearer.run();
		});
		runButton.setPrefWidth(width);
		runButton.setPrefHeight(height);
		clearButton = makeButton("Clear", e -> clear());
		clearButton.setPrefWidth(width);
		clearButton.setPrefHeight(height);

		buttonPanel.getChildren().addAll(runButton, clearButton);
		this.getChildren().add(buttonPanel);
	}

	private Button makeButton(String label, EventHandler<ActionEvent> handler) {
		Button ret = new Button(label);
		ret.setOnAction(handler);
		return ret;
	}

	private void enter() {
		String code = tp.getText();
		try {
			commandConsumer.accept(code);
			historyConsumer.accept(code);
			clear();
		} catch (SLogoException e) {
			new ErrorWindow(e.getMessage());
		}
	}

	private void clear() {
		tp.clear();
	}

}
