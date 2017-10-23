package view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.API.BackgroundOptionListener;
import view.API.CanvasDisplay;

/**
 * Class for setting up the canvas where turtles are placed.
 * 
 * @author DavidTran
 *
 */
public class CanvasView extends Pane implements CanvasDisplay, BackgroundOptionListener {

	private Color myBackgroundColor = Color.WHITE;
	/**
	 * Constructor
	 */
	public CanvasView(double width, double height) {
		this.setBackgroundColor(myBackgroundColor);
		this.setScaleY(-1);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		System.out.println("Canvas width: " + width + " height: " + height);
	
	}
	
	@Override
	public void setBackgroundColor(Color c) {
		this.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
		
	}

	@Override
	public void backgroundColorChange(Color color) {
		this.setBackgroundColor(color);
		
	}

}
