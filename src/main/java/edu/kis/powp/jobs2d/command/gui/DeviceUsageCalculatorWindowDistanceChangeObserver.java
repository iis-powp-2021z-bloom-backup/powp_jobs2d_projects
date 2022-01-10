package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.observer.Subscriber;

public class DeviceUsageCalculatorWindowDistanceChangeObserver implements Subscriber {

	private DeviceUsageCalculatorWindow deviceUsageCalculatorWindow;

	public DeviceUsageCalculatorWindowDistanceChangeObserver(DeviceUsageCalculatorWindow deviceUsageCalculatorWindow) {
		super();
		this.deviceUsageCalculatorWindow = deviceUsageCalculatorWindow;
	}

	@Override
	public void update() {
		deviceUsageCalculatorWindow.updateDistanceFields();
	}

}
