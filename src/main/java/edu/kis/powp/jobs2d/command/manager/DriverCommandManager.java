package edu.kis.powp.jobs2d.command.manager;

import java.util.List;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.gui.CommandManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class DriverCommandManager implements CommandManager {
	private DriverCommand currentCommand = null;
	private DriverManager driverManager = null;

	private Publisher changePublisher = new Publisher();

	/**
	 * Set current command.
	 * 
	 * @param commandList Set the command as current.
	 */
	@Override
	public synchronized void setCurrentCommand(DriverCommand commandList) {
		this.currentCommand = commandList;
		changePublisher.notifyObservers();
	}

	/**
	 * Set current command.
	 * 
	 * @param commandList list of commands representing a compound command.
	 * @param name        name of the command.
	 */
	public synchronized void setCurrentCommand(List<DriverCommand> commandList, String name) {
		setCurrentCommand(new CompoundCommand(commandList, name));
	}

	/**
	 * Return current command.
	 * 
	 * @return Current command.
	 */
	public synchronized DriverCommand getCurrentCommand() {
		return currentCommand;
	}

	@Override
	public synchronized void clearCurrentCommand() {
		currentCommand = null;
	}

	@Override
	public synchronized String getCurrentCommandString() {
		if (getCurrentCommand() == null) {
			return "No command loaded";
		} else
			return getCurrentCommand().toString();
	}

	@Override
	public Publisher getChangePublisher() {
		return changePublisher;
	}

	@Override
	public synchronized void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void runCommand() {
		getCurrentCommand().execute(DriverFeature.getDriverManager().getCurrentDriver());
	}
}
