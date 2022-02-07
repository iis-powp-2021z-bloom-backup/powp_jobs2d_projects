package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;

public class FeatureManager {

    private final DrawerFeature drawerFeature;
    private final CommandsFeature commandsFeature;
    private final DeviceUsageFeature deviceUsageFeature;
    private final RecordingFeature recordingFeature;
    private final DriverFeature driverFeature;

    public FeatureManager(Application app) {
        this.drawerFeature = new DrawerFeature(app);
        this.commandsFeature = new CommandsFeature();
        this.deviceUsageFeature = new DeviceUsageFeature();
        this.recordingFeature = new RecordingFeature(app, DriverFeature.getDriverManager());
        this.driverFeature = new DriverFeature(app);
    }

    public void setupFeatures() {
        drawerFeature.setup();
        commandsFeature.setup();
        deviceUsageFeature.setup();
        recordingFeature.setup();
        driverFeature.setup();
        driverFeature.setUpDriverNameLabelChangeManager();
    }

    public DrawerFeature getDrawerFeature() {
        return drawerFeature;
    }

    public CommandsFeature getCommandsFeature() {
        return commandsFeature;
    }

    public DeviceUsageFeature getDeviceUsageFeature() {
        return deviceUsageFeature;
    }

    public RecordingFeature getRecordingFeature() {
        return recordingFeature;
    }

    public DriverFeature getDriverFeature() {
        return driverFeature;
    }
}
