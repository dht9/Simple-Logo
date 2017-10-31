package view.Animation;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * Class that manages the viewing of turtles.
 * 
 * @author DavidTran
 */

public class TurtleViewManager {

	private List<TurtleView> myTurtleList;
	private List<String> myColorList;
	private List<String> myImageNameList;
	private final CanvasView myParent;
	private Image myImage;
	private Runnable myUpdateColorList;

	/*
	 * Constructor
	 */
	public TurtleViewManager(CanvasView parent, Image image, List<String> imgList, List<String> colorList, Runnable updateColorList) {
		myTurtleList = new ArrayList<TurtleView>();
		myParent = parent;
		myImage = image;
		myImageNameList = imgList;
		myColorList = colorList;
		myUpdateColorList = updateColorList;
	}

	/**
	 * Called when a turtle is added.
	 */
	public void addTurtle() {
		TurtleView turtleView = new TurtleView(myParent, myImage, myImageNameList, myColorList, myUpdateColorList);		
		myTurtleList.add(turtleView);
		myParent.getChildren().add(turtleView.getImageView());
	}

	/**
	 * Called when a TurtleListener is needed for the backend.
	 * 
	 * @return a new TurtleListener
	 */
	public TurtleListener getNewListener() {
		addTurtle();
		return myTurtleList.get(myTurtleList.size()-1);
	}
	
	/**
	 * Removes TurtleListeners from the program.
	 */
	public void clear() {
		myTurtleList.clear();
		myParent.getChildren().clear();
	}

	/**
	 * Updates color list for each turtle
	 */
	public void updateColorList(List<String> colorList) {
		for (TurtleView tv : myTurtleList)
			tv.updateColorList(colorList);	
	}
}
