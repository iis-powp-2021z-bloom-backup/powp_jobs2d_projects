package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;

public class FeatureManager {
    private final Application application;
    private final DrawerFeature drawerFeature;
    private final CommandsFeature commandsFeature;
    private final DeviceUsageFeature deviceUsageFeature;
    private final RecordingFeature recordingFeature;
    private final DriverFeature driverFeature;

				//driverFeature.setUpDriverNameLabelChangeManager();
    public FeatureManager(Application app){
        this.application = app;
        this.drawerFeature = new DrawerFeature(app);
        this.commandsFeature = new CommandsFeature();
        this.deviceUsageFeature=  new DeviceUsageFeature();
        this.recordingFeature = new RecordingFeature(app,DriverFeature.getDriverManager());
        this.driverFeature = new DriverFeature(app);
    }

    public static void setup(){
        
    }
}
