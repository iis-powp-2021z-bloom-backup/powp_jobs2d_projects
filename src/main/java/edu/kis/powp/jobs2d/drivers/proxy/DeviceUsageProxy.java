package edu.kis.powp.jobs2d.drivers.proxy;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.manager.DeviceUsageManager;


public class DeviceUsageProxy implements Job2dDriver {
	private final Job2dDriver driver;
	private final DeviceUsageManager deviceUsageManager;
	private int posX = 0;
	private int posY = 0;

	public DeviceUsageProxy(Job2dDriver driver, DeviceUsageManager deviceUsageManager) {
		this.driver = driver;
		this.deviceUsageManager = deviceUsageManager;
	}

	@Override
	public void setPosition(int x, int y) {
		this.driver.setPosition(x, y);
		this.deviceUsageManager.notifyHeadMovePerformed(calculateDistance(x, y));
		this.posX = x;
		this.posY = y;

	}

	@Override
	public void operateTo(int x, int y) {
		this.driver.operateTo(x, y);
		this.deviceUsageManager.notifyOperationPerformed(calculateDistance(x, y));
		this.posX = x;
		this.posY = y;
	}

	@Override
	public String toString() {
		return "Device Usage Proxy";
	}

	private double calculateDistance(int x, int y) {
		return Math.sqrt(Math.pow(x - posX, 2) + Math.pow(y - posY, 2));
	}
}
