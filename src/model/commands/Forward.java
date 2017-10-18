package model.commands;

import java.util.Map;

import model.Command;
import model.CommandDef;
import model.Turtle;

public class Forward implements Command {

	private Command input;

	public Forward(Command par1) {
		input = par1;
	}

	@Override
	public double execute(Turtle t, Map<String, CommandDef> commands, Map<String, Double> variables) {
		double result = input.execute(t, commands, variables);
		t.setXY(t.getX() - result * Math.sin(Math.toDegrees(t.getHeading())),
				t.getY() + result * Math.sin(Math.toDegrees(t.getHeading())));
		return result;
	}

}
