package model;

public interface CommandDef {
	public Command build(TokenDispenser dispenser) throws SLogoException;
}
