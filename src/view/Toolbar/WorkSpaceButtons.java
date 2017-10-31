package view.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import controller.Driver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.SubcomponentViewAPI;

public class WorkSpaceButtons implements SubcomponentViewAPI {

	private static final String WORKSPACE_DIRECTORY = "src/resources/data/";
	private Button newButton;
	private Button saveButton;
	private VBox view;
	private Runnable newWorkspace;
	private Consumer<String> saveConsumer;
	private Consumer<String> loadConsumer;
	private Set<String> fileList;
	private ChoiceBox<String> cb;
	HBox panel;

	public WorkSpaceButtons(Runnable newWorkspace, Consumer<String> saveConsumer, Consumer<String> loadConsumer) {

		view = new VBox(new Label("Workspace Manager"));
		panel = new HBox();
		newButton = makeButton("New", e -> newFile());
		saveButton = makeButton("Save", e -> saveFile());

		cb = new ChoiceBox<>();
		panel.getChildren().addAll(newButton, saveButton, cb);
		updateFileList();
		makeChoiceBox();

		view.getChildren().add(panel);
		view.setAlignment(Pos.BASELINE_CENTER);

		this.newWorkspace = newWorkspace;
		this.saveConsumer = saveConsumer;
		this.loadConsumer = loadConsumer;

	}

	private Button makeButton(String name, EventHandler<ActionEvent> e) {
		Button ret = new Button(name);
		ret.setOnAction(e);
		ret.setId("toolbar-button");
		return ret;
	}

	private void newFile() {
		newWorkspace.run();
	}

	private void saveFile() {

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Save Workspace Dialog");
		dialog.setHeaderText("Save Workspace");
		dialog.setContentText("File name for saved workspace:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
			saveConsumer.accept(WORKSPACE_DIRECTORY + result.get());

	}

	private void makeChoiceBox() {
		panel.getChildren().remove(cb);

		cb = new ChoiceBox<>();
		panel.getChildren().add(cb);

		for (String name : fileList)
			cb.getItems().add(name);

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				cb.getSelectionModel().clearAndSelect(newValue.intValue());
				System.out.println(newValue.intValue());
				loadConsumer.accept(WORKSPACE_DIRECTORY + cb.getSelectionModel().getSelectedItem().toString());
			}
		});

		cb.setMinWidth(cb.getWidth());

	}

	@Override
	public Parent getParent() {
		return view;
	}

	// used if we want a drop-down
	public void updateFileList() {

		List<String> allFiles = new ArrayList<String>();
		fileList = new HashSet<>();

		File file = new File(WORKSPACE_DIRECTORY);
		File[] files = file.listFiles();

		for (File f : files) {
			allFiles.add(f.getName());
		}

		for (String s : allFiles) {
			if (s.contains("_")) {
				s = s.substring(0, s.indexOf("_"));
				fileList.add(s);
			}
		}
		System.out.println(fileList);

		makeChoiceBox();

	}

}
