package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class SetXY implements Command {

	private Command x;
	private Command y;
	
	public SetXY(Command newX, Command newY) {
		x = newX;
		y = newY;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double newX = x.execute(t, commands, variables);
		double newY = y.execute(t, commands, variables);
		double dx = newX - t.getX();
		double dy = newY - t.getY();
		t.setXY(newX, newY);
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

}
