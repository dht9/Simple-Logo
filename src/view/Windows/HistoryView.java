package view.Windows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.SubcomponentViewAPI;
import view.ErrorWindow;
import model.SLogoException;
import model.SaverLoader;

/**
 * Class that allows users to see command history.
 * 
 * @author taekwhunchung
 *
 */

public class HistoryView implements SubcomponentViewAPI, SaveLoadAPI {

	private static final String DELIMITER = "&&&";
	private static final ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private Button clearButton;
	private Button undoButton;
	private Button redoButton;
	private String lastCommand;
	private List<String> historyList;
	private Stack<String> undone;
	private Text text;
	private VBox myHistory;
	private ScrollPane scrollPane;
	private GridPane view;

	private Consumer<String> myCommandConsumer;
	private Runnable myReset;

	public HistoryView(double width, double height, Consumer<String> commandConsumer, Runnable reset) {
		historyList = new ArrayList<>();
		undone = new Stack<>();

		myCommandConsumer = commandConsumer;
		myReset = reset;

		view = new GridPane();
		view.setMinHeight(height);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(60);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(10);
		RowConstraints row4 = new RowConstraints();
		row4.setPercentHeight(10);
		RowConstraints row5 = new RowConstraints();
		row5.setPercentHeight(10);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(100);

		view.getRowConstraints().addAll(row1, row2, row3, row4, row5);
		view.getColumnConstraints().addAll(col1);

		text = new Text(myResources.getString(("HistoryView")));
		text.setFill(Color.WHITE);

		myHistory = new VBox();
		myHistory.setPrefWidth(width);
		myHistory.setMinHeight(height);
		myHistory.setId("var-VBox");
		scrollPane = new ScrollPane();
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setContent(myHistory);

		clearButton = makeButton("Clear History", e -> clear());
		undoButton = makeButton("Undo", e -> undo());
		redoButton = makeButton("Redo", e -> redo());

		view.add(text, 0, 0);
		view.add(scrollPane, 0, 1);
		view.add(clearButton, 0, 2);
		view.add(undoButton, 0, 3);
		view.add(redoButton, 0, 4);
	}

	/*************************** PUBLIC METHODS ********************************/

	/**
	 * Updates the history view.
	 * 
	 * @param newCode
	 *            command to be added to the view.
	 */
	public void updateHistory(String newCode) {
		lastCommand = newCode;
		historyList.add(lastCommand);
		Hyperlink t = new Hyperlink(newCode);
		t.setOnAction(event -> {
			try {
				clearUndone();
				myCommandConsumer.accept(newCode);
				updateHistory(newCode);
			} catch (SLogoException e) {
				new ErrorWindow(e.getMessage());
			}
		});
		myHistory.getChildren().addAll(t);
	}

	@Override
	public Parent getParent() {
		return view;
	}

	@Override
	public void save(String filePath) {
		StringBuilder sb = new StringBuilder();
		for (String s : historyList)
			sb.append(s + " " + DELIMITER + " ");
		SaverLoader.save(sb.toString(), filePath);
	}

	@Override
	public void load(String filePath) {
		historyList = new ArrayList<String>(
				Arrays.asList(((String) SaverLoader.load(filePath)).split("\\s" + DELIMITER + "\\s")));
		myHistory.getChildren().removeAll(myHistory.getChildren());
		for (String s : historyList) {
			Hyperlink t = new Hyperlink(s);
			t.setOnAction(event -> {
				try {
					myCommandConsumer.accept(s);
				} catch (SLogoException e) {
					new ErrorWindow(e.getMessage());
				}
			});
			myHistory.getChildren().add(t);
		}
	}

	/**
	 * Clears the undo stack.
	 */
	public void clearUndone() {
		undone.clear();
	}

	/*************************** PRIVATE METHODS ********************************/

	private Button makeButton(String name, EventHandler<ActionEvent> e) {
		Button ret = new Button(name);
		ret.setOnAction(e);
		return ret;
	}

	private void redo() {
		clear();
		int size = undone.size();
		if (size != 0) {
			historyList.add(undone.pop());
		}
		resetAndRun();
	}

	private void undo() {
		// TODO make redo, store undone commands in a separate Stack, throw error/do
		// nothing when undoing too many times
		clear();
		int size = historyList.size();
		if (size != 0) {
			undone.add(historyList.remove(size - 1));
		}
		resetAndRun();
	}

	private void clear() {
		myHistory.getChildren().clear();
	}

	private void resetAndRun() {
		myReset.run();
		List<String> newHistory = new ArrayList<>();
		newHistory.addAll(historyList);
		historyList.clear();
		for (String command : newHistory) {
			myCommandConsumer.accept(command);
			updateHistory(command);
		}
	}

}
