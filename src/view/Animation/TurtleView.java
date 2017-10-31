package view.Animation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ImmutableTurtle;
import view.ErrorWindow;

/**
 * Class to make the turtle viewable.
 *
 * @author DavidTran
 */
public class TurtleView implements TurtleListener {

	private static final double WIDTH = 35;
	private static final double HEIGHT = 35;
	private List<Image> myImageList;
	private List<String> myImageNameList;
	private List<String> myColorList;

	private ImmutableTurtle turtle;
	private ImageView myView;
	private int myPenColorIndex;
	private double myPenThickness;
	private boolean myPenIsDown;
	private CanvasView myParent;
	private double myHeading;
	private int myShapeIndex;
	private Runnable myUpdateColorList;

	private double myOffsetX;
	private double myOffsetY;
	private double myPrevNewX;
	private double myPrevNewY;

	public TurtleView(CanvasView parent, Image image, List<String> imgNameList, List<String> colorList,
			Runnable updateColorList) {

		myView = setupImageView(image);
		myParent = parent;

		myImageNameList = imgNameList;
		myColorList = colorList;
		myUpdateColorList = updateColorList;

		myImageList = new ArrayList<>();
		for (String s : myImageNameList) {
			Image fileImage = new Image(getClass().getClassLoader().getResourceAsStream("resources/images/" + s));
			myImageList.add(fileImage);
		}
	}

	/*************************** PUBLIC METHODS ********************************/

	@Override
	public void setTurtle(ImmutableTurtle turtle) {

		myHeading = turtle.getHeading();
		myPenColorIndex = turtle.getPenColorIndex();
		myPenThickness = turtle.getPenSize();
		myPenIsDown = turtle.getPenDown();
		myView.setVisible(turtle.isVisible());

		this.turtle = turtle;

		myOffsetX = myParent.getMaxWidth() / 2;
		myOffsetY = myParent.getMaxHeight() / 2;

		myView.setX(turtle.getX() + myOffsetX);
		myView.setY(turtle.getY() + myOffsetY);
		myPrevNewX = turtle.getX() + myOffsetX;
		myPrevNewY = turtle.getY() + myOffsetY;

	}

	@Override
	public void locationChange(double newX, double newY) {
		// compensate for center offset since center if not (0,0), returns value
		// referenced from center.
		double offsetNewX = newX + myOffsetX;
		double offsetNewY = newY + myOffsetY;
		// System.out.println("Turtle ID: " + myID);
		// System.out.println("prevX: " + myPrevNewX + " | prevY: " + myPrevNewY);
		// System.out.println("offsetNewX: " + offsetNewX + " | offsetNewY: " +
		// offsetNewY);

		double coordInsideX = offsetNewX % (myOffsetX * 2);
		double coordInsideY = offsetNewY % (myOffsetY * 2);

		boolean rightBound = offsetNewX - myPrevNewX + myView.getX() >= myOffsetX * 2;
		boolean upperBound = offsetNewY - myPrevNewY + myView.getY() >= myOffsetY * 2;
		boolean leftBound = offsetNewX - myPrevNewX + myView.getX() <= 0;
		boolean lowerBound = offsetNewY - myPrevNewY + myView.getY() <= 0;

		double prevX = myPrevNewX;
		double prevY = myPrevNewY;
		myPrevNewX = offsetNewX;
		myPrevNewY = offsetNewY;
		double distX = offsetNewX - prevX + myView.getX();
		double distY = offsetNewY - prevY + myView.getY();

		Line line;

		if (rightBound) {
			while (distX >= (myOffsetX * 2)) {

				if (distY > prevY + 0.05 || distY < prevY - 0.05)
					distY = myView.getY()
							+ (offsetNewY - prevY) * ((myOffsetX * 2 - myView.getX()) / (offsetNewX - prevX));
				if (myPenIsDown) {
					line = new Line(myView.getX(), myView.getY(), myOffsetX * 2, distY);
					line.setStroke(Color.valueOf(myColorList.get(myPenColorIndex)));
					line.setStrokeWidth(myPenThickness);
					myParent.getChildren().add(line);
				}
				myView.setX(0);
				myView.setY(distY);

				offsetNewX = offsetNewX - myOffsetX * 2;
				distX = offsetNewX - prevX + myView.getX();
				// System.out.println("OOB");
				// System.out.println("Now x= " + myView.getX() + " | y=" + myView.getY());
				// System.out.println("Now offsetnewx= " + offsetNewX + " | offsetnewy=" +
				// offsetNewY);
			}
		}

		if (myPenIsDown) {
			line = new Line(myView.getX(), myView.getY(), coordInsideX, coordInsideY);
			line.setStroke(Color.valueOf(myColorList.get(myPenColorIndex)));
			line.setStrokeWidth(myPenThickness);
			myParent.getChildren().add(line);
		}

		myView.setX(coordInsideX);
		myView.setY(coordInsideY);

		// System.out.println("OOB");
		// System.out.println("Final x= " + myView.getX() + " | y=" + myView.getY());

		// System.out.println("LayoutX: " + myOffsetX + " LayoutY: " + myOffsetY);
		// System.out.println("myX: " + myView.getX() + " | myY: " + myView.getY());
		// System.out.println("newX: " + newX + " | newY: " + newY);
		// System.out.println("offsetNewX: " + offsetNewX + " | offsetNewY: " +
		// offsetNewY);

	}

	@Override
	public void headingChange(double newHeading) {
		myHeading = -newHeading;
		myView.setRotate(180 - myHeading);
	}

	@Override
	public void penChange(boolean newState) {
		myPenIsDown = newState;
	}

	@Override
	public void penColorChange(int index) {
		myPenColorIndex = index;
	}

	@Override
	public void penSizeChange(double thickness) {
		myPenThickness = thickness;
	}

	@Override
	public void visibilityChange(boolean visibility) {
		myView.setVisible(visibility);
	}

	@Override
	public void activeToggle(boolean active) {
		if (!active)
			myView.setStyle("-fx-background-color:transparent");
		else
			myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0)");
	}

	@Override
	public void clearScreen() {
		Iterator<Node> it = myParent.getChildren().iterator();
		while (it.hasNext()) {
			Node n = it.next();
			if (n instanceof Line)
				it.remove();
		}
	}

	@Override
	public void shapeChange(int index) {
		try {
			myShapeIndex = index;
			myView.setImage(myImageList.get(myShapeIndex));
		} catch (Exception e) {
			new ErrorWindow(e.getMessage());
		}
	}

	@Override
	public void backgroundColorChange(int index) {
		myParent.backgroundColorChange(index);
	}

	/**
	 * Returns image associated with turtle.
	 */
	public ImageView getImageView() {
		return myView;
	}

	@Override
	public void addToPalette(int index, int rVal, int gVal, int bVal) {
		if (index < myColorList.size()) {
			myColorList.set(index, "rgb(" + Integer.toString(rVal) + "," + Integer.toString(gVal) + ","
					+ Integer.toString(bVal) + ")");
		}
		System.out.println("Changed palette: " + myColorList);
		myUpdateColorList.run();
	}
	
	/**
	 * Called when color list is updated
	 * @param colorList
	 */
	public void updateColorList(List<String> colorList) {
		myColorList = colorList;
	}

	/*************************** PRIVATE METHODS ********************************/

	private ImageView setupImageView(Image image) {
		ImageView ret = new ImageView(image);
		ret.setFitWidth(WIDTH);
		ret.setFitHeight(HEIGHT);
		ret.setLayoutX(-WIDTH / 2);
		ret.setLayoutY(-HEIGHT / 2);
		ret.setRotate(180);
		ret.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0)");
		ret.setOnMouseClicked(e -> clicked());
		ret.setOnMouseEntered(e -> entered());
		ret.setOnMouseExited(e -> exited());
		return ret;
	}

	/**
	 * Make toggling viewable.
	 */
	private void clicked() {
		// System.out.println("Clicked turtle");
		turtle.setActive(!turtle.isActive());
	}

	/**
	 * Make mouse hovering noticeable.
	 */
	private void entered() {
		if (!turtle.isActive())
			myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
	}

	private void exited() {
		if (!turtle.isActive())
			myView.setStyle("-fx-background-color:transparent;");
	}
}
