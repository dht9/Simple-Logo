package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import view.Animation.TurtleListener;

/**
 * A representation of the state of the turtle at a given time. Notifies all
 * TurtleListeners when changes are made to the state.
 * 
 * Code masterpiece: This class implements an Observer Design Pattern. This
 * design pattern defines a one-to-many dependency between SingularTurtle
 * (back-end) and TurtleListener (front-end). This class represents the "one" in
 * the dependency because it updates several listeners.
 * 
 * This class is well designed because whenever the SingularTurtle changes its
 * state(s), its listeners are notified with the exact change(s) due to the
 * Observer design pattern. Essentially, the listeners are observing any changes
 * to the SingularTurtle. This class provides encapsulation because it does not
 * control how the TurtleListener defines its particular method behaviors. The
 * SingularTurtle simply calls those methods to notify the TurtleListener that a
 * change of turtle state has occurred.
 *
 * @author Aaron Paskin, David Tran, Ian Eldridge-Allegra
 */
public class SingularTurtle implements ImmutableTurtle, Turtle {

	private List<TurtleListener> listeners;
	private int id;
	private double x;
	private double initX;
	private double y;
	private double initY;
	private double heading;
	private double initHeading;
	private boolean penDown;
	private boolean isVisible;
	private boolean isActive;
	private int penColorIndex;
	private int backgroundColorIndex;
	private double penSize;
	private int shapeIndex;
	private Supplier<Integer> numTurtles;

	public static final int DEFAULT_PEN_COLOR_INDEX = 1;
	public static final int DEFAULT_PEN_SIZE = 1;
	public static final int DEFAULT_BACKGROUND_COLOR_INDEX = 0;
	public static final int DEFAULT_SHAPE_INDEX = 0;

	/**
	 * @param x0
	 *            starting x
	 * @param y0
	 *            starting y
	 * @param heading0
	 *            starting heading
	 * @param id
	 */
	public SingularTurtle(double x0, double y0, double heading0, int id) {
		this.id = id;
		x = initX = x0;
		y = initY = y0;
		heading = initHeading = heading0;
		penDown = true;
		isVisible = true;
		isActive = true;
		penColorIndex = DEFAULT_PEN_COLOR_INDEX;
		backgroundColorIndex = DEFAULT_BACKGROUND_COLOR_INDEX;
		penSize = DEFAULT_PEN_SIZE;
		shapeIndex = DEFAULT_SHAPE_INDEX;
		listeners = new ArrayList<>();
	}

	/**
	 * @param tL
	 *            The TurtleListener to add
	 */
	public void addTurtleListener(TurtleListener tL) {
		listeners.add(tL);
		tL.setTurtle(this);
	}

	/**
	 * @param numTurtles
	 *            A Supplier that will always provide the number of turtles
	 */
	public void setNumTurtles(Supplier<Integer> numTurtles) {
		this.numTurtles = numTurtles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#getNumTurtles()
	 */
	public int getNumTurtles() {
		return numTurtles.get();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#getID()
	 */
	public int getID() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#getX()
	 */
	public double getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#getY()
	 */
	public double getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#getHeading()
	 */
	public double getHeading() {
		return heading;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#getPenDown()
	 */
	public boolean getPenDown() {
		return penDown;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#isVisible()
	 */
	public boolean isVisible() {
		return isVisible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#isActive()
	 */
	public boolean isActive() {
		return isActive;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#getPenColorIndex()
	 */
	public int getPenColorIndex() {
		return penColorIndex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#getPenSize()
	 */
	public double getPenSize() {
		return penSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#getShapeIndex()
	 */
	@Override
	public int getShapeIndex() {
		return shapeIndex;
	}

	/**
	 * @param newX
	 *            The target x location
	 * @param newY
	 *            The target y location
	 * @return The distance moved
	 */
	public double setXY(double newX, double newY) {
		double dist = Math.sqrt(Math.pow(x - newX, 2) + Math.pow(y - newY, 2));
		x = newX;
		y = newY;
		for (TurtleListener tL : listeners)
			tL.locationChange(newX, newY);
		return dist;
	}

	/**
	 * @param newHeading
	 *            The target heading
	 */
	public void setHeading(double newHeading) {
		heading = newHeading;
		for (TurtleListener tL : listeners)
			tL.headingChange(newHeading);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setPenDown(boolean)
	 */
	public void setPenDown(boolean down) {
		penDown = down;
		for (TurtleListener tL : listeners)
			tL.penChange(down);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		isVisible = visible;
		for (TurtleListener tL : listeners)
			tL.visibilityChange(visible);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setPenColor(int)
	 */
	public void setPenColor(int index) {
		penColorIndex = index;
		for (TurtleListener tL : listeners)
			tL.penColorChange(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setPenSize(double)
	 */
	@Override
	public void setPenSize(double pixels) {
		penSize = pixels;
		for (TurtleListener tL : listeners)
			tL.penSizeChange(penSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setBackgroundColor(int)
	 */
	public void setBackgroundColor(int index) {
		backgroundColorIndex = index;
		for (TurtleListener tL : listeners)
			tL.backgroundColorChange(backgroundColorIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setShapeIndex(int)
	 */
	@Override
	public void setShapeIndex(int index) {
		shapeIndex = index;
		for (TurtleListener tL : listeners)
			tL.shapeChange(index);
	}

	/**
	 * Returns to original position, heading, visibility, and pen position, then
	 * notifies the listeners to clear
	 */
	public double clearScreen() {
		double ret = setXY(initX, initY);
		setHeading(initHeading);
		setPenDown(true);
		setVisible(true);
		setPenColor(DEFAULT_PEN_COLOR_INDEX);
		for (TurtleListener tL : listeners)
			tL.clearScreen();
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.ImmutableTurtle#setActive(boolean)
	 */
	@Override
	public void setActive(boolean active) {
		isActive = active;
		for (TurtleListener listener : listeners)
			listener.activeToggle(active);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#forward(model.Command, model.CommandManager,
	 * model.VariableManager)
	 */
	public double forward(Command par, CommandManager commands, VariableManager variables) {
		double result = par.execute(this, commands, variables);
		setXY(getX() - result * Math.sin(Math.toRadians(getHeading())),
				getY() + result * Math.cos(Math.toRadians(getHeading())));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#left(model.Command, model.CommandManager,
	 * model.VariableManager)
	 */
	public double left(Command input, CommandManager commands, VariableManager variables) {
		double result = input.execute(this, commands, variables);
		setHeading(getHeading() + result);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setHeading(model.Command, model.CommandManager,
	 * model.VariableManager)
	 */
	public double setHeading(Command input, CommandManager commands, VariableManager variables) {
		double result = input.execute(this, commands, variables);
		setHeading(result);
		return Math.abs(getHeading() - result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setXY(model.Command, model.Command, model.CommandManager,
	 * model.VariableManager)
	 */
	public double setXY(Command xCor, Command yCor, CommandManager commands, VariableManager variables) {
		double xC = xCor.execute(this, commands, variables);
		double yC = yCor.execute(this, commands, variables);
		return setXY(xC, yC);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setTowards(model.Command, model.Command,
	 * model.CommandManager, model.VariableManager)
	 */
	public double setTowards(Command xCor, Command yCor, CommandManager commands, VariableManager variables) {
		double newHeading = Math.toDegrees(Math.atan2(getX() - xCor.execute(this, commands, variables),
				yCor.execute(this, commands, variables) - getY()));
		if (newHeading < 0)
			newHeading += 360;
		double dtheta = newHeading - getHeading();
		setHeading(newHeading);
		return dtheta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.Turtle#setPalette(int, int, int, int)
	 */
	@Override
	public void setPalette(int indexVal, int rVal, int gVal, int bVal) {
		for (TurtleListener tL : listeners)
			tL.addToPalette(indexVal, rVal, gVal, bVal);
	}
}
