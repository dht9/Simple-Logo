package view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.API.StringListenerAPI;
import view.API.TurtleListenerAPI;
import view.API.VariableListenerAPI;
import view.API.ViewAPI;

public class View implements ViewAPI {

	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int SCREEN_WIDTH = 900;
	private static final int SCREEN_HEIGHT = 700;
	
	private Stage myStage;
	private Scene myScene;
	private Timeline myTimeline;
	private GridPane myRoot;

	
	/**
	 * Constructor for setting up animation.
	 * @param stage
	 */
	public View(Stage stage) {
		myStage = stage;
		myTimeline = setupTimeline();
		setupLayout();

		myTimeline.play();
	}
	
	/****************PUBLIC METHODS*******************/
	
	@Override
	public TurtleListenerAPI getTurtleListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VariableListenerAPI getVariableListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringListenerAPI getCommandListener() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/***************PRIVATE METHODS*******************/

	/**
	 * Sets up Timeline for animations.
	 */
	private Timeline setupTimeline() {
		Timeline tl = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		tl.setCycleCount(Animation.INDEFINITE);
		tl.getKeyFrames().add(frame);
		return tl;
	}

	private void step(double elaspedTime) {
		// do stuff
	}
	
	/**
	 * Sets up the general layout of the scene.
	 */
	private void setupLayout() {
		myRoot = new GridPane();
		myScene = new Scene(myRoot, SCREEN_WIDTH, SCREEN_HEIGHT);
		ColumnConstraints col1 = new ColumnConstraints();
		ColumnConstraints col2 = new ColumnConstraints();
		ColumnConstraints col3 = new ColumnConstraints();
		RowConstraints row1 = new RowConstraints();
		col1.setPercentWidth(25);
		col2.setPercentWidth(50);
		col3.setPercentWidth(25);
		row1.setPercentHeight(100);
		myRoot.getColumnConstraints().addAll(col1, col2, col3);
		myRoot.getRowConstraints().addAll(row1);
		
		ScrollPane leftSP = new ScrollPane();
		leftSP.setFitToWidth(true);
		leftSP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		leftSP.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		myRoot.add(leftSP, 0, 0, 1, 1);
		
		myStage.setScene(myScene);
		myStage.show();
		
	}




}
