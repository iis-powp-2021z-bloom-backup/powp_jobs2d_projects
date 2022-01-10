package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.DeviceUsageManager;

public class DeviceUsageFeature {

	private static DeviceUsageManager deviceUsageManager;

	public static void setupDeviceUsageManager() {
		deviceUsageManager = new DeviceUsageManager();
	}

	public static DeviceUsageManager getDeviceUsageManager() {
		return deviceUsageManager;
	}
}
