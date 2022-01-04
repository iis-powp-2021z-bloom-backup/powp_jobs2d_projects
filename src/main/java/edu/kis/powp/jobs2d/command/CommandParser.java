package edu.kis.powp.jobs2d.command;

import java.io.FileNotFoundException;
import java.util.List;

public interface CommandParser {
	void fillListFromFile() throws FileNotFoundException;
	List<DriverCommand> getCommandList();
}
