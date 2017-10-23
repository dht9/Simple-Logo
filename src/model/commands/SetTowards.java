package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

public class SetTowards implements Command {

	private Command x;
	private Command y;

	public SetTowards(Command xCor, Command yCor) {
		x = xCor;
		y = yCor;
	}

	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		double newHeading = Math.toDegrees(
				Math.atan2(t.getX() - x.execute(t, commands, variables), 
						y.execute(t, commands, variables) - t.getY()));
		if(newHeading < 0)
			newHeading += 360;
		double dtheta = newHeading - t.getHeading();
		t.setHeading(newHeading);
		return dtheta;
	}
}
