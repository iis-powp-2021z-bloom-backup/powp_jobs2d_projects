package edu.kis.powp.jobs2d.command.manager;
import java.util.List;

import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.gui.CommandManager;
import edu.kis.powp.jobs2d.command.visitor.VisitorCommand;
import edu.kis.powp.jobs2d.command.visitor.VisitorCounter;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Publisher;

/**
 * Driver command Manager.
 */
public class DriverCommandManager implements CommandManager {
	private DriverCommand currentCommand = null;
	private DriverManager driverManager = null;
	private Publisher changePublisher = new Publisher();

	public synchronized DriverCommand setCommand(DriverCommand commandList) {
		this.currentCommand = commandList;
		return commandList;
	}
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
		if (getCurrentCommand() == null ) {
			return "No command loaded, press 'run command' to show statistics";
		} else{
			currentCommand = getCurrentCommand();

			VisitorCounter commandVisitorCounter = new VisitorCounter();
			CommandsFeature.getDriverCommandManager().getCurrentCommand().accept((VisitorCommand) commandVisitorCounter);

			String command = getCurrentCommand().toString();
			int operateToCounter = commandVisitorCounter.getCounter();
			int setPositionCounter = commandVisitorCounter.getCounter();
			int numberOfSubcommands = operateToCounter + setPositionCounter;

			return("Current command: " + command + "\n"
					+ "Number of Subcommands: " + numberOfSubcommands + "\n"
					+ "Number of operateTo command: " + operateToCounter + "\n"
					+ "Number of setPosition command: " + setPositionCounter + "\n"
			);
		}
	}


	@Override
	public Publisher getChangePublisher() {
		return changePublisher;
	}

	public synchronized void setDriverManager(DriverManager driverManager) {
		this.driverManager = driverManager;
	}

	@Override
	public void runCommand() {
		getCurrentCommand().execute(DriverFeature.getDriverManager().getCurrentDriver());
		getCurrentCommandString();
	}
}
