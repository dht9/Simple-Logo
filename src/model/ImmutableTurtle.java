package model;

/**
 * A Turtle that is primarily view-only. 
 * @see Turtle
 */
public interface ImmutableTurtle {
	public int getID();
	
	public double getX();

	public double getY();

	public double getHeading();

	public boolean getPenDown();

	public boolean isVisible();

	public int getPenColorIndex();
	
	public void setActive(boolean active);

	public boolean isActive();
	
	public double getPenSize();
	
	public int getShapeIndex();
}
