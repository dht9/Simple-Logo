package view.Windows;

import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import view.SubcomponentViewAPI;

/**
 * Class for Scroll panes.
 * 
 * @author DavidTran
 *
 */
public class ScrollPaneView implements SubcomponentViewAPI {

	ScrollPane sp;
	
	public ScrollPaneView () {
		sp = createScrollPane();
	}
	private ScrollPane createScrollPane() {
		ScrollPane sp = new ScrollPane();
		sp.setFitToWidth(true);
		sp.setPannable(true);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
//		sp.setVvalue(1.0);
		return sp;
	}
	
	@Override
	public Parent getParent() {
		return sp;
	}
}
