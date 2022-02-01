package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.DeviceUsageManager;

public class DeviceUsageFeature implements FeatureInterface {

    private static DeviceUsageManager deviceUsageManager;

    public DeviceUsageFeature() {
        setup();
    }

    /**
     * Setup Device Usage Manager.
     * Parameters required by implemented interface, not used here.
     */
    @Override
    public void setup() {
        deviceUsageManager = new DeviceUsageManager();
    }

    public static DeviceUsageManager getDeviceUsageManager() {
        return deviceUsageManager;
    }

}
