package view;

import java.util.function.Consumer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.SLogoException;
import view.API.StringListener;
import view.API.TurtleListener;
import view.API.VariableListener;
import view.API.ViewAPI;

/**
 * Class that displays the GUI and SLogo animations.
 * 
 * @author DavidTran
 *
 */
public class View implements ViewAPI {

	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int SCREEN_WIDTH = 1000;
	private static final int SCREEN_HEIGHT = 700;
	private static final String STYLESHEET = "/resources/view/view.css";

	private static final String TURTLE_IMAGE = "Turtle_up.png";

	private Stage myStage;
	private Scene myScene;
	private Timeline myTimeline;

	private GridPane myGrid;
	private ScrollPane myLeftSP;
	private ScrollPane myRightSP;
	private VBox myLeftVBox;
	private VBox myRightVBox;

	private CanvasView myCanvas;
	private TurtleView myTurtleView;
	private TextPromptView myTextPrompt;

	private UserDefinedCommandView myUDCView;
	private VariableView myVarView;
	private ReferenceView myRefView;
	private HistoryView myHistoryView;

	private ToolbarView myToolbarView;

	/**
	 * Constructor for setting up animation.
	 * 
	 * @param stage
	 */
	public View(Stage stage, Consumer<String> commandConsumer) {
		myStage = stage;
		myStage.setTitle("SLogo Interpreter");
		start(commandConsumer);
	}

	/**************** PUBLIC METHODS *******************/

	public void start(Consumer<String> commandConsumer) {
		myTimeline = setupTimeline();
		setupLayout();
		addAnimationComponents();
		addScrollPaneComponents();
		addTextPrompt(commandConsumer);
		addToolbar();
		myTimeline.play();
	}

	@Override
	public TurtleListener getTurtleListener() {
		return myTurtleView;
	}

	@Override
	public VariableListener getVariableListener() {
		return myVarView;
	}

	@Override
	public StringListener getCommandListener() {
		return myRefView;
	}
	
	@Override
	public StringListener getUserDefinedCommandListener() {
		return myUDCView;
	}

	@Override
	public void display(SLogoException e) {
		// TODO
		System.out.println(e.getMessage());
		showError(e.getMessage());
	}

	/*************** PRIVATE METHODS *******************/

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

	/**
	 * Steps to update interface.
	 */
	private void step(double elaspedTime) {
		// Read command

		// Pass into execute
	}

	/**
	 * Sets up the general layout of the scene.
	 */
	private void setupLayout() {
		myGrid = new GridPane();

		myScene = new Scene(myGrid, SCREEN_WIDTH, SCREEN_HEIGHT);
		// myScene.getStylesheets().add(getClass().getResource("/resources/view/view.css").toExternalForm());
		myScene.getStylesheets().add(STYLESHEET);
		myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(25);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(50);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(25);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(10);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(70);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(20);

		myGrid.getColumnConstraints().addAll(col1, col2, col3);
		myGrid.getRowConstraints().addAll(row1, row2, row3);

		myStage.setScene(myScene);
		myStage.show();

	}

	private void handleKeyInput(KeyCode code) {
		myTurtleView.handleInput(code);
		System.out.println("press");
	}

	/**
	 * Add canvas and turtle components for viewing animations.
	 */
	private void addAnimationComponents() {
		double[][] dims = getGridDimensions();
		myCanvas = new CanvasView(dims[0][1], dims[1][1]);
		myCanvas.setLayoutX(myCanvas.getMaxWidth() / 2);
		myCanvas.setLayoutY(myCanvas.getMaxHeight() / 2);

		myGrid.add(myCanvas, 1, 1);
		GridPane.setConstraints(myCanvas, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER);

		// FOR TESTING
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("resources/images/" + TURTLE_IMAGE));
		myTurtleView = new TurtleView(myCanvas, image);

		myCanvas.getChildren().add(myTurtleView.getImage());
	}

	/**
	 * Add text prompt where commands are entered and run.
	 */
	private void addTextPrompt(Consumer<String> commandConsumer) {
		double[][] dims = getGridDimensions();
		myTextPrompt = new TextPromptView(dims[0][1], dims[1][2], s -> {
			commandConsumer.accept(s);
			myHistoryView.updateHistory(s);
		});
		myGrid.add(myTextPrompt, 1, 2, 2, 1);
	}

	/**
	 * Create left and right major scrollpanes.
	 * 
	 * @return
	 */
	private ScrollPane createScrollPane() {
		ScrollPane sp = new ScrollPane();
		sp.setFitToWidth(true);
		sp.setPannable(true);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);

		return sp;
	}

	/**
	 * Add subcomponents of major scroll panes.
	 */
	private void addScrollPaneComponents() {
		double dims[][] = getGridDimensions();
		
		myLeftSP = createScrollPane();
		myLeftVBox = new VBox();
		myLeftSP.setContent(myLeftVBox);
		myGrid.add(myLeftSP, 0, 1, 1, 2);

		myRightSP = createScrollPane();
		myRightVBox = new VBox();
		myRightSP.setContent(myRightVBox);
		myGrid.add(myRightSP, 2, 1, 1, 2);

		myUDCView = new UserDefinedCommandView((dims[1][1] + dims[1][2]) / 2);
		myVarView = new VariableView((dims[1][1] + dims[1][2]) / 2);
		myRefView = new ReferenceView((dims[1][1] + dims[1][2]) / 2);
		myHistoryView = new HistoryView((dims[1][1] + dims[1][2]) / 2);

		myLeftVBox.getChildren().add(myUDCView.getParent());
		myLeftVBox.getChildren().add(myVarView.getParent());
		myRightVBox.getChildren().add(myRefView.getParent());
		myRightVBox.getChildren().add(myHistoryView.getParent());

	}

	private void addToolbar() {
		myToolbarView = new ToolbarView(SCREEN_WIDTH);
		// set a listener for background color changes.
		myToolbarView.getBackgroundOptionView().addBackgroundOptionListener(myCanvas);
		myToolbarView.getImageOptionView().addTurtleImageListener(myTurtleView);
		myToolbarView.getPenOptionView().addPenOptionListener(myTurtleView);
		myGrid.add(myToolbarView.getParent(), 0, 0);
	}

	/**
	 * @return array containing dimensions of cells in grid
	 */
	private double[][] getGridDimensions() {
		double[][] ret = new double[4][4];
		try {
			java.lang.reflect.Method m = myGrid.getClass().getDeclaredMethod("getGrid");
			m.setAccessible(true);
			ret = (double[][]) m.invoke(myGrid);
			// for (int i = 0; i < ret.length; i++) {
			// for (int j = 0; j < ret[0].length; j++)
			// System.out.println(i + "," + j + " " + ret[i][j]);
			// }
			// System.out.println(ret);
		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());
		}
		return ret;
	}

	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
