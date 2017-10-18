package view;

import javafx.scene.paint.Color;
import model.ImmutableTurtle;
import view.API.TurtleListenerAPI;

/**
 * Listens for modifications to a turtle's state -- its methods must be called
 * when these changes occur
 *
 */
public class TurtleListener implements TurtleListenerAPI {

	@Override
	public void setTurtle(ImmutableTurtle turtle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void locationChange(double dx, double dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void headingChange(double dtheta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void penChange(boolean down) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visibilityChange(boolean isVisible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void penColorChange(Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearScreen() {
		// TODO Auto-generated method stub
		
	}
	
}