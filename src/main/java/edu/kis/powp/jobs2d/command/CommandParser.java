package edu.kis.powp.jobs2d.command;

public interface CommandParser {

	void parse(String commandsInput);

	DriverCommand getParsedCommand();
}
