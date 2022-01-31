package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.manager.DeviceUsageManager;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import javax.swing.*;

public class DeviceUsageFeature implements FeatureInterface{

	private static DeviceUsageManager deviceUsageManager;

	@Override
	public void setup(Application application, JPanel freePanel, DriverManager drvMgr) {
		deviceUsageManager = new DeviceUsageManager();
	}

	public static DeviceUsageManager getDeviceUsageManager() {
		return deviceUsageManager;
	}

}
