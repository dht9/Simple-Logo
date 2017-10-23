package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class SetPosition implements Command {

	private Command xCor;
	private Command yCor;

	public SetPosition(Command xCor, Command yCor) {
		this.xCor = xCor;
		this.yCor = yCor;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double x = xCor.execute(t, commands, variables);
		double y = yCor.execute(t, commands, variables);
		double dx = x - t.getX();
		double dy = y - t.getY();
		t.setXY(x,y);
		return Math.sqrt(dx*dx+dy*dy);
	}

}
