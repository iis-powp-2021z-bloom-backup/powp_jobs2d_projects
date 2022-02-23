package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.CommandReader;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.visitor.VisitorCommand;
import edu.kis.powp.jobs2d.command.visitor.VisitorCounter;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class CommandManagerWindow extends JFrame implements WindowComponent {

	private List<Subscriber> observerList;
	private boolean observersDeleted = false;
	private final CommandManager commandManager;
	private final JTextArea currentCommandField;

	private String observerListString;
	private final JTextArea observerListField;

	/**
	 *
	 */

	private static final long serialVersionUID = 9204679248304669948L;

	public CommandManagerWindow(CommandManager commandManager) {
		this.setTitle("Command Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		this.commandManager = commandManager;

		GridBagConstraints c = new GridBagConstraints();

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(observerListField, c);
		updateObserverListField();

		currentCommandField = new JTextArea("");
		currentCommandField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(currentCommandField, c);
		updateCurrentCommandField();

		JButton btnClearCommand = new JButton("Clear command");
		btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearCommand, c);

		JButton bntGiveCommand = new JButton("Give command");
		bntGiveCommand.addActionListener((ActionEvent e) -> this.giveCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(bntGiveCommand, c);

		JButton btnClearObservers = new JButton("Delete observers");
		btnClearObservers.addActionListener((ActionEvent e) -> this.deleteObservers(btnClearObservers));
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearObservers, c);

		JButton btnRunCommand = new JButton("Run command");
		btnRunCommand.addActionListener((ActionEvent e) -> this.runCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnRunCommand, c);

	}

	private void clearCommand() {
		commandManager.clearCurrentCommand();
		updateCurrentCommandField();
	}

	private void runCommand() {
		commandManager.runCommand();
		updateCurrentCommandField();
	}

	private void giveCommand() {
		final JFileChooser fc = new JFileChooser();
		DriverCommand newCommands = null;

		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			CommandReader commandParser = new CommandReader(file);
			try {
				commandParser.fillListFromFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
			newCommands = commandParser.getCommandList();
		}
		commandManager.setCurrentCommand(newCommands);
	}

	public void updateCurrentCommandField() {
		currentCommandField.setText(commandManager.getCurrentCommandString());

	}

	public void deleteObservers(JButton resetButton) {
		if (observersDeleted) {
			resetObservers(resetButton);
		} else {
			this.observerList = this.commandManager.getChangePublisher().getSubscribers();
			commandManager.getChangePublisher().clearObservers();
			this.updateObserverListField();
			observersDeleted = true;
			resetButton.setText("Reset observers");
		}
	}

	public void resetObservers(JButton deleteButton) {
		commandManager.getChangePublisher().clearObservers();
		observersDeleted = false;
		deleteButton.setText("Delete observers");

		if (observerList != null) {
			for (Subscriber subscriber : observerList) {
				this.commandManager.getChangePublisher().addSubscriber(subscriber);
			}
		}
		this.updateObserverListField();

	}

	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = commandManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		this.setVisible(!this.isVisible());
	}


}
