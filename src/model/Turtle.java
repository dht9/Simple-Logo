package model;

import javafx.scene.paint.Color;
import view.API.TurtleListener;

/**
 * A representation of the state of the turtle at a given time. Notifies all
 * TurtleListeners when changes are made to the state.
 *
 */
public class Turtle implements ImmutableTurtle, UserTurtle {
	
	private TurtleListener listener;
	private double x;
	private double initX;
	private double y;
	private double initY;
	private double heading;
	private double initHeading;
	private boolean penDown;
	private boolean isVisible;
	private Color penColor;
	
	public static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	
	public Turtle(double x0, double y0, double heading0) {
		x = initX = x0;
		y = initY = y0;
		heading = initHeading = heading0;
		penDown = true;
		isVisible = true;
		penColor = DEFAULT_PEN_COLOR;
	}
	
	// need to add to interface
	public void addTurtleListener(TurtleListener tL) {
		listener = tL;
		tL.setTurtle(this, this);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeading() {
		return heading;
	}

	public boolean getPenDown() {
		return penDown;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public Color getPenColor() {
		return penColor;
	}

	public void setXY(double newX, double newY) {
		x = newX;
		y = newY;
		listener.locationChange(newX, newY);
	}

	public void setHeading(double newHeading) {
		heading = newHeading;
		listener.headingChange(newHeading);
	}

	public void setPenDown(boolean down) {
		penDown = down;
		listener.penChange(down);
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
		listener.visibilityChange(visible);
	}

	@Override
	public void setPenColor(Color color) {
		penColor = color;
		listener.penColorChange(color);
	}

    @Override
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void rotate(double dtheta) {
        heading += dtheta;
    }

    /**
	 * Returns to original position, heading, visibility, and pen position, then notifies the listeners to clear
	 */
	public void clearScreen() {
		setXY(initX, initY);
		setHeading(initHeading);
		setPenDown(true);
		setVisible(true);
		setPenColor(DEFAULT_PEN_COLOR);
		listener.clearScreen();
	}
}
