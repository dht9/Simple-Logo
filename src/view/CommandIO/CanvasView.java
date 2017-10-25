package view.CommandIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.API.CommandIOAPI.CanvasDisplay;
import view.API.ToolbarAPI.BackgroundOptionListener;

/**
 * Class for setting up the canvas where turtles are placed.
 * 
 * @author DavidTran
 *
 */
public class CanvasView extends Pane implements CanvasDisplay, BackgroundOptionListener {

	private Color myBackgroundColor = Color.WHITE;
	private ResourceBundle myResources = ResourceBundle.getBundle("resources.view/choicebox");
	private List<String> colorList;
	/**
	 * Constructor
	 */
	public CanvasView(double width, double height) {
		this.setBackgroundColor(myBackgroundColor);
		this.setScaleY(-1);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		//System.out.println("Canvas width: " + width + " height: " + height);
		colorList = new ArrayList<String>(
				Arrays.asList(myResources.getString("BackgroundColors").replaceAll("\\s+", "").split(",")));
	
	}
	
	@Override
	public void setBackgroundColor(Color c) {
		this.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
		
	}

	@Override
	public void backgroundColorChange(int colorIndex) {
		this.setBackgroundColor(Color.valueOf(colorList.get(colorIndex)));
		
	}

}
