package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.command.manager.DeviceUsageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class DeviceUsageCalculatorWindow extends JFrame implements WindowComponent {

	private static final DecimalFormat distanceFormat = new DecimalFormat("###.##");
	private final DeviceUsageManager deviceUsageManager;

	private final JTextArea distanceUsed;
	private final JTextArea distanceTravelled;


	public DeviceUsageCalculatorWindow(DeviceUsageManager deviceUsageManager) {
		this.setTitle("Command Manager");
		this.setSize(400, 200);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		this.deviceUsageManager = deviceUsageManager;

		GridBagConstraints c = new GridBagConstraints();

		JTextArea distanceUsedLabel = new JTextArea("Distance device used = ");
		distanceUsedLabel.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(distanceUsedLabel, c);

		distanceUsed = new JTextArea(distanceFormat.format(this.deviceUsageManager.getDistanceDeviceUsed()));
		distanceUsed.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.weighty = 1;
		content.add(distanceUsed, c);

		JTextArea distanceTraveledLabel = new JTextArea("Distance device travelled = ");
		distanceTraveledLabel.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(distanceTraveledLabel, c);

		distanceTravelled = new JTextArea(distanceFormat.format(this.deviceUsageManager.getDistanceTravelled()));
		distanceTravelled.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 1;
		c.weighty = 1;
		content.add(distanceTravelled, c);

		JButton btnClearDistanceCommand = new JButton("Clear device travel distance");
		btnClearDistanceCommand.addActionListener((ActionEvent e) -> this.deviceUsageManager.clearDistanceTravelled());
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.weightx = 2;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearDistanceCommand, c);

		JButton btnClearUsageCommand = new JButton("Clear device used distance");
		btnClearUsageCommand.addActionListener((ActionEvent e) -> this.deviceUsageManager.clearDistanceUsed());
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.weightx = 2;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnClearUsageCommand, c);
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

	public void updateDistanceFields() {
		this.distanceTravelled.setText(distanceFormat.format(this.deviceUsageManager.getDistanceTravelled()));
		this.distanceUsed.setText(distanceFormat.format(this.deviceUsageManager.getDistanceDeviceUsed()));
	}

}
