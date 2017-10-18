package model;

import javafx.scene.paint.Color;

/**
 * A view-only version of 
 * @see Turtle
 */
public interface ImmutableTurtle {
	public double getX();
	public double getY();
	public double getHeading();
	public boolean getPenDown();
	public boolean isVisible();
	public Color getPenColor();
}
