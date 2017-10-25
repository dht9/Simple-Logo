package view.Toolbar;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import view.API.ToolbarAPI.LanguageListener;
import view.API.ToolbarAPI.LanguageOptionDisplay;

/**
 * Class that allows users to select a language from a choice box.
 * 
 * @author taekwhunchung
 *
 */

public class LanguageOptionView implements LanguageOptionDisplay {
	
	private VBox optionView;
	private Label prompt;
	private ChoiceBox<String> cb;
	private List<String> languageList;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private List<LanguageListener> listeners = new ArrayList<>();;
	
	public LanguageOptionView() {
		
		optionView = new VBox();
		
		prompt = new Label(myResources.getString("LanguagePrompt"));
		
		languageList = new ArrayList<String>(
				Arrays.asList(myResources.getString("Languages").replaceAll("\\s+", "").split(",")));
		
		cb = new ChoiceBox<String>(FXCollections.observableArrayList(languageList));
		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				for (LanguageListener listener : listeners)
					listener.languageChange(languageList.get(newValue.intValue()));
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
	public void addLanguageOptionListener(LanguageListener l) {
		listeners.add(l);
	}
}
