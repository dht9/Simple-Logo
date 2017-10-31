package view.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Class that allows users to select a language from a choice box.
 * 
 * @author taekwhunchung
 *
 */

public class LanguageOptionView extends OptionView implements LanguageOptionAPI {

	private List<String> languageList;
	private List<LanguageListener> listeners = new ArrayList<>();
	private static final String PROMPT = "LanguagePrompt";

	public LanguageOptionView() {
		super(PROMPT);

		languageList = new ArrayList<String>(
				Arrays.asList(myResources.getString("Languages").replaceAll("\\s+", "").split(",")));

		for (String language : languageList)
			cb.getItems().add(language);

		cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				for (LanguageListener listener : listeners)
					listener.languageChange(languageList.get(newValue.intValue()));
			}
		});
	}

	@Override
	public void addLanguageOptionListener(LanguageListener l) {
		listeners.add(l);
	}
}
