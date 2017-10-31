package model.commands;

import model.Command;
import model.CommandManager;
import model.Turtle;
import model.VariableManager;

/**
 * @author Aaron Paskin
 * @author Ian Eldridge-Allegra
 */
public class ATan implements Command {
	private static final long serialVersionUID = -7821142903737347711L;
	private Command a;
	
	public ATan(Command a) {
		this.a = a;
	}
	
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		return Math.toDegrees(Math.atan(a.execute(t, commands, variables)));
	}

}
