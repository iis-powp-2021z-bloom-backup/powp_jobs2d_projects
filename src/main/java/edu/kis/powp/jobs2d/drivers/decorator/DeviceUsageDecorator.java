package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.DeviceUsageManager;


public class DeviceUsageDecorator implements Job2dDriver {
	private final Job2dDriver driver;
	private final DeviceUsageManager deviceUsageManager;
	private int posX = 0;
	private int posY = 0;

	public DeviceUsageDecorator(Job2dDriver driver, DeviceUsageManager deviceUsageManager) {
		this.driver = driver;
		this.deviceUsageManager = deviceUsageManager;
	}

	@Override
	public void setPosition(int x, int y) {
		this.driver.setPosition(x, y);
		this.deviceUsageManager.updateHeadDistance(calculateDistance(x, y));
		this.posX = x;
		this.posY = y;

	}

	@Override
	public void operateTo(int x, int y) {
		this.driver.operateTo(x, y);
		this.deviceUsageManager.updateDeviceUsedDistance(calculateDistance(x, y));
		this.posX = x;
		this.posY = y;
	}

	@Override
	public String toString() {
		return "Driver with device usage";
	}

	private double calculateDistance(int x, int y) {
		return Math.sqrt(Math.pow(x - posX, 2) + Math.pow(y - posY, 2));
	}
}
