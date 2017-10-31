package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class SetPalette implements Command{

	private static final long serialVersionUID = 1144339788966397020L;
	private Command index;
	private Command r;
	private Command g;
	private Command b;

	public SetPalette(Command index, Command r, Command g, Command b) {
		this.index = index;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		int indexVal = (int) Math.round(index.execute(t,commands, variables));
		int rVal = (int) Math.round(r.execute(t,commands, variables));
		int gVal = (int) Math.round(g.execute(t,commands, variables));
		int bVal = (int) Math.round(b.execute(t,commands, variables));
		rVal = constrain(rVal);
		gVal = constrain(gVal);
		bVal = constrain(bVal);
		
		t.setPalette(indexVal, rVal, gVal, bVal);
		return indexVal;
	}

	private int constrain(int val) {
		return constrain(val, 0, 255);
	}
	
	private int constrain(int val, int min, int max) {
		return Math.min(max,  Math.max(min, val));
	}
	
}
