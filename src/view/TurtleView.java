package view;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.ImmutableTurtle;
//import view.API.PenOptionListener;
import view.API.TurtleListener;

/**
 * Class to make the turtle viewable.
 * 
 * @author DavidTran
 *
 */
public class TurtleView implements TurtleListener {

	private static final double WIDTH = 35;
	private static final double HEIGHT = 35;

	private ImageView myView;

	private Color myPenColor;
	private boolean myPenIsDown;
	private Pane myParent;
	private double myHeading;
	private boolean myIsToggled;

	private double offsetX;
	private double offsetY;

	public TurtleView(Pane parent, Image image) {
		// for testing
		myView = new ImageView(image);
		myView.setFitWidth(WIDTH);
		myView.setFitHeight(HEIGHT);
		myView.setLayoutX(-WIDTH / 2);
		myView.setLayoutY(-HEIGHT / 2);
		myView.setX(0);
		myView.setY(0);
		headingChange(0);

		myView.setRotate(180);
		// myHeading = myView.getRotate();

		setMouseEvents();

		myPenColor = Color.WHITE;
		myPenIsDown = true;
		myIsToggled = false;
		myParent = parent;
	}

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		offsetX = myView.getParent().getLayoutX();
		offsetY = myView.getParent().getLayoutY();

		myView.setX(turtle.getX() + offsetX);
		myView.setY(turtle.getY() + offsetY);

		myHeading = turtle.getHeading() + 180;
		myPenColor = turtle.getPenColor();
		myPenIsDown = turtle.getPenDown();
		myView.setVisible(turtle.isVisible());

	}

	// TESTING
	public ImageView getImage() {
		return myView;
	}

	/**
	 * Allow turtle imageviews to change with mouse interactions
	 */
	private void setMouseEvents() {

		myView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// do stuff (toggle turtle)
				System.out.println("Clicked turtle");
				if (myIsToggled)
					myView.setStyle("-fx-background-color:transparent");
				else
					myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 15, 0, 0, 0)");
				myIsToggled = !myIsToggled;
			}
		});

		myView.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (!myIsToggled)
					myView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0)");
			}
		});

		myView.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				if (!myIsToggled)
					myView.setStyle("-fx-background-color:transparent;");
			}
		});

	}

	@Override
	public void locationChange(double newX, double newY) {
		// System.out.println("x :" + myView.getX() + " " + myView.getY());

		// compensate for center offset since center if not (0,0)
		double offsetNewX = newX + offsetX;
		double offsetNewY = newY + offsetY;

		Line line = new Line(myView.getX(), myView.getY(), offsetNewX, offsetNewY);
		line.setStroke(myPenColor);

		if (myPenIsDown)
			myParent.getChildren().add(line);

		myView.setX(offsetNewX);
		myView.setY(offsetNewY);

		// System.out.println("LayoutX: " + offsetX + " LayoutY: " + offsetY);
		// System.out.println("myX: " + myView.getX() + " | myY: " + myView.getY());
		// System.out.println("newX: " + newX + " | newY: " + newY);
		// System.out.println("offsetNewX: " + offsetNewX + " | offsetNewY: " +
		// offsetNewY);
	}

	@Override
	public void headingChange(double newHeading) {
		// TODO Auto-generated method stub
		// create an animation that rotates the shape
		// myView.setRotate(dtheta);
		double newAngle = -newHeading;
		myView.setRotate(180 - newAngle);
		System.out.println("NewHeading: " + (-newHeading));
	}

	@Override
	public void penChange(boolean newState) {
		myPenIsDown = newState;
	}

	@Override
	public void penColorChange(Color color) {
		myPenColor = color;

	}

	@Override
	public void visibilityChange(boolean visibility) {
		myView.setVisible(visibility);

	}

	@Override
	public void clearScreen() {
		// remove image from pane
		List<Node> nodes = ((Pane) myView.getParent()).getChildren();
		nodes.clear();
		nodes.add(myView);
	}

	@Override
	public void handleInput(KeyCode code) {
		System.out.println(code);

		// must change the back-end turtle!!!

		if (myIsToggled) {
			switch (code) {
			case W:
				locationChange(myView.getX() - offsetX, myView.getY() - offsetY + 1);
				break;
			case S:
				locationChange(myView.getX() - offsetX, myView.getY() - offsetY - 1);
				break;
			case A:
				locationChange(myView.getX() - offsetX - 1, myView.getY() - offsetY);
				break;
			case D:
				locationChange(myView.getX() - offsetX + 1, myView.getY() - offsetY);
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void imageChange(Image image) {
		myView.setImage(image);
	}

}
