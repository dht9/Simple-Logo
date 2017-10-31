package view.Windows;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.ImmutableTurtle;
import view.Animation.TurtleListener;

/**
 * Class allowing users to see attributes of current turtle.
 * 
 * @author DavidTran
 *
 */
public class TurtleStateView extends Window implements TurtleListener {
	private List<ImmutableTurtle> myTurtles = new ArrayList<>();
	private static final ResourceBundle myResources = ResourceBundle.getBundle("resources.view/view");

	private List<String> myImageNameList;
	private List<String> myColorList;

	public TurtleStateView(double height, List<String> imgList, List<String> colorList) {
		super(height);
		ta.appendText(myResources.getString("TurtleStateView"));
		myImageNameList = imgList;
		myColorList = colorList;
	}

	// add to interface
	private void update() {
		ta.clear();
		ta.appendText(myResources.getString("TurtleStateView") + "\n\n");

		for (ImmutableTurtle turtle : myTurtles) {

			if (turtle.isActive()) {
				ta.appendText("ID: " + Integer.toString(turtle.getID()) + "\n");
				ta.appendText(String.format("X: %5.3f\n", turtle.getX()));
				ta.appendText(String.format("Y: %5.3f\n", turtle.getY()));
				ta.appendText(String.format("Heading: %5.1f\n", (turtle.getHeading() % 360)));
				ta.appendText("Pen Down: " + Boolean.toString(turtle.getPenDown()) + "\n");
				ta.appendText("Pen Color: " + myColorList.get(turtle.getPenColorIndex()) + "\n");
				ta.appendText("Pen Thickness: " + turtle.getPenSize() + "\n");
				ta.appendText("Shape: " + myImageNameList.get(turtle.getShapeIndex()) + "\n");
				ta.appendText("Visibility: " + Boolean.toString(turtle.isVisible()) + "\n\n");
			}
		}
	}

	public void clear() {
		ta.clear();
		myTurtles.clear();
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		myTurtles.add(turtle);
		// System.out.println("added turtle");
		update();
	}

	@Override
	public void locationChange(double newX, double newY) {
		update();
	}

	@Override
	public void headingChange(double newAngle) {
		update();
	}

	@Override
	public void penChange(boolean down) {
		update();
	}

	@Override
	public void visibilityChange(boolean isVisible) {
		update();
	}

	@Override
	public void penColorChange(int colorIndex) {
		update();
	}

	@Override
	public void clearScreen() {
		update();
	}

	@Override
	public void activeToggle(boolean active) {
		update();
	}

	@Override
	public void penSizeChange(double thickness) {
		update();
	}

	@Override
	public void shapeChange(int index) {
		update();
	}

	@Override
	public void backgroundColorChange(int index) {
		// do nothing
	}

	@Override
	public void addToPalette(int index, int rVal, int gVal, int bVal) {
		if (index < myColorList.size()) {
			myColorList.set(index, "rgb(" + Integer.toString(rVal) + "," + Integer.toString(gVal) + ","
					+ Integer.toString(bVal) + ")");
		}
	}

	/**
	 * Called when color list needs to be updated.
	 * 
	 * @param colorList
	 *            updated color list
	 */
	public void updateColorList(List<String> colorList) {
		myColorList = colorList;
		update();
	}

}
