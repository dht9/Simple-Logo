package view.Windows;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Pane representing the basic instructions for using SLogo.
 * 
 * @author DavidTran
 *
 */
public class ReferenceView extends Window implements StringListener {

	public ReferenceView(double height) {
		super(height);
		ta.appendText(myResources.getString("ReferenceView") + "\n");
	}

	@Override
	public void changedMap(Set<String> set, Map<String, List<String>> userCs) {
		ta.clear();
		ta.appendText(myResources.getString("ReferenceView") + "\n\n");
		for (String key : set) {
			ta.appendText(key + "\n");
		}
		ta.setScrollTop(0);
		
	}

}
