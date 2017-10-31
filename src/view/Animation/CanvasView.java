package view.Animation;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.SaverLoader;
import view.Windows.SaveLoadAPI;

/**
 * Class for setting up the canvas where turtles are placed.
 * 
 * @author DavidTran
 *
 */
public class CanvasView extends Pane implements SaveLoadAPI {

	private Color DEFAULT_COLOR = Color.WHITE;
	private List<String> colorList;
	private int myColorIndex;

	/**
	 * Constructor
	 */
	public CanvasView(double width, double height, List<String> list) {
		this.setBackground(new Background(new BackgroundFill(DEFAULT_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setScaleY(-1);
		this.setMaxWidth(width);
		this.setMaxHeight(height);
		this.setLayoutX(this.getMaxWidth() / 2);
		this.setLayoutY(this.getMaxHeight() / 2);
		colorList = list;
	}

	/**
	 * Called when background color is changed.
	 * 
	 * @param colorIndex
	 *            index of the color to be set.
	 */
	public void backgroundColorChange(int colorIndex) {
		this.setBackground(new Background(
				new BackgroundFill(Color.valueOf(colorList.get(colorIndex)), CornerRadii.EMPTY, Insets.EMPTY)));
		myColorIndex = colorIndex;
	}

	@Override
	public void save(String filePath) {
		SaverLoader.save(myColorIndex, filePath);
	}

	@Override
	public void load(String filePath) {
		backgroundColorChange((int) SaverLoader.load(filePath));
	}

	/**
	 * Called when color list if updated.
	 * 
	 * @param list
	 *            updated colorlist
	 */
	public void update(List<String> list) {
		colorList = list;
	}
}