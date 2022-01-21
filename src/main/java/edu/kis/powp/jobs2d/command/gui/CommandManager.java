package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.observer.Publisher;

public interface CommandManager {

	void clearCurrentCommand();
	void runCommand();
	String getCurrentCommandString();
	void setCurrentCommand(DriverCommand commandList);
	Publisher getChangePublisher();
	void setDriverManager(DriverManager driverManager);
}
