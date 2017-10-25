package view.CommandIO;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.ImmutableTurtle;
import view.API.CommandIOAPI.TurtleListener;

/**
 * Created by DavidTran on 10/23/17.
 */
public class TurtleViewManager implements TurtleListener {

	List<TurtleView> turtleList = new ArrayList<TurtleView>();
	Pane myParent;
	Image myImage;

	public TurtleViewManager(Pane parent, Image image) {
		myParent = parent;
		myImage = image;
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		TurtleView turtleView = new TurtleView(myParent, myImage);
		turtleView.setTurtle(turtle);
		turtleList.add(turtleView);
		myParent.getChildren().add(turtleView.getImageView());

	}

	@Override
	public void locationChange(double newX, double newY) {
		for (TurtleView turtle : turtleList) {
			turtle.locationChange(newX, newY);
		}
	}

	@Override
	public void headingChange(double newAngle) {
		for (TurtleView turtle : turtleList) {
			turtle.headingChange(newAngle);
		}
	}

	@Override
	public void penChange(boolean down) {

	}

	@Override
	public void visibilityChange(boolean isVisible) {
		for (TurtleView turtle : turtleList) {
			turtle.visibilityChange(isVisible);
		}
	}

	@Override
	public void penColorChange(int colorIndex) {
		for (TurtleView turtle : turtleList) {
			turtle.penColorChange(colorIndex);
		}
	}

	@Override
	public void clearScreen() {
		for (TurtleView turtle : turtleList) {
			turtle.clearScreen();
		}
	}

	@Override
	public void imageChange(int imageIndex) {
		for (TurtleView turtle : turtleList) {
			turtle.imageChange(imageIndex);
		}
	}
}
