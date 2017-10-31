package model;

/**
 * Some commands only make sense when asked of a TurtleManager, rather than a
 * single turtle. This class encapsulates that error-checking for its
 * subclasses.
 * 
 * @author Ian Eldridge-Allegra
 *
 */
public abstract class TurtleManagerSpecificCommand implements Command {
	private static final long serialVersionUID = 4234355250566310877L;

	/* (non-Javadoc)
	 * @see model.Command#execute(model.Turtle, model.CommandManager, model.VariableManager)
	 */
	@Override
	public double execute(Turtle t, CommandManager commands, VariableManager variables) {
		if (t instanceof TurtleManager)
			return executeOnManager((TurtleManager) t, commands, variables);
		throw new SLogoException("NestedCommand");
	}

	/**
	 * The same as execute, but now t is guaranteed to be a TurtleManager
	 * 
	 * @param t A TurtleManager to execute commands on
	 * @param commands Known commands
	 * @param variables Known variables
	 * @return The result of the command
	 */
	protected abstract double executeOnManager(TurtleManager t, CommandManager commands, VariableManager variables);

}
