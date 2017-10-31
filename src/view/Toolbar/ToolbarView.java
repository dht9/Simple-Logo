package view.Toolbar;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import view.ErrorWindow;
import view.SubcomponentViewAPI;

/**
 * Class allowing users to view and use toolbar functions.
 * 
 * @author DavidTran, taekwhunchung
 *
 */

public class ToolbarView implements ToolbarAPI, SubcomponentViewAPI {

	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");
	private static final double NODE_SPACING = 50;

	private HBox myToolbar;
	private WorkSpaceButtons myWorkSpaceButtons;
	private BackgroundOptionView myBackgroundOptionView;
	private PenOptionView myPenOptionView;
	private PenSlider myPenSlider;
	private PenButtons myPenButtons;
	private TurtleImageOptionView myImageOptionView;
	private LanguageOptionView myLanguageOptionView;
	private List<String> myImageNameList;
	private List<String> myColorList;
	private ViewSelector myViewSelector;
	private WindowObservable<String> myActiveView;
	private Hyperlink myHelpLink;
	private BiConsumer<String, String> myCommandConsumer;

	public ToolbarView(double width, List<String> imgList, List<String> colorList, Runnable newWorkspace,
			Consumer<String> saveConsumer, Consumer<String> loadConsumer, BiConsumer<String, String> commandConsumer,
			WindowObservable<String> activeView) {

		myToolbar = new HBox(NODE_SPACING);
		myToolbar.setAlignment(Pos.CENTER);
		myToolbar.setMinWidth(width);

		myImageNameList = imgList;
		myColorList = colorList;
		myCommandConsumer = commandConsumer;
		myActiveView = activeView;

		myWorkSpaceButtons = new WorkSpaceButtons(newWorkspace, saveConsumer, loadConsumer);
		makeSubcomponents();
		makeHelpLink();

		myToolbar.getChildren().addAll(myWorkSpaceButtons.getParent(), myBackgroundOptionView.getParent(),
				myPenOptionView.getParent(), myPenSlider.getParent(), myPenButtons.getParent(),
				myImageOptionView.getParent(), myLanguageOptionView.getParent(), myHelpLink);

	}
	
	/******************** PUBLIC METHODS ********************/

	@Override
	public Parent getParent() {
		return myToolbar;
	}

	@Override
	public BackgroundOptionView getBackgroundOptionView() {
		return myBackgroundOptionView;
	}

	@Override
	public PenOptionView getPenOptionView() {
		return myPenOptionView;
	}

	@Override
	public LanguageOptionView getLanguageOptionView() {
		return myLanguageOptionView;
	}

	@Override
	public WorkSpaceButtons getWorkSpaceButtons() {
		return myWorkSpaceButtons;
	}
	
	/******************** PRIVATE METHODS ********************/

	private void makeHelpLink() {

		myHelpLink = new Hyperlink();
		myHelpLink.setText("Help");
		myHelpLink.setId("help-hyperlink");

		myHelpLink.setOnAction(e -> {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI(myResources.getString("HelpURL")));
				} catch (IOException e1) {
					e1.printStackTrace();
					new ErrorWindow(e1.getMessage());
				} catch (URISyntaxException e1) {
					new ErrorWindow(e1.getMessage());
				}
			}
		});

	}

	private void makeSubcomponents() {
		myBackgroundOptionView = new BackgroundOptionView(myColorList, myCommandConsumer);
		myPenOptionView = new PenOptionView(myColorList, myCommandConsumer);
		myPenSlider = new PenSlider(myCommandConsumer);
		myPenButtons = new PenButtons(myCommandConsumer);
		myImageOptionView = new TurtleImageOptionView(myImageNameList, myCommandConsumer);
		myLanguageOptionView = new LanguageOptionView();
		addViewSelector(myActiveView);
	}

	private void addViewSelector(WindowObservable<String> activeView) {
		myViewSelector = new ViewSelector(activeView);
		Button openViewSelector = createButton(myResources.getString("ToolbarViewSelectorPrompt"));
		openViewSelector.setOnAction(e -> myViewSelector.run());
	}

	private Button createButton(String string) {
		Button button = new Button(string);
		button.setId("toolbar-button");
		myToolbar.getChildren().add(button);
		myToolbar.setAlignment(Pos.BASELINE_CENTER);
		return button;

	}

}
