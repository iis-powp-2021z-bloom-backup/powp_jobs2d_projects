package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.observer.Subscriber;

public class DriverObserver implements Subscriber {

    private final DriverManager driverManager;
    private final Application application;

    public DriverObserver(DriverManager driverManager, Application application) {
        this.driverManager = driverManager;
        this.application = application;
    }

    @Override
    public void update() {
        application.updateInfo(driverManager.getCurrentDriverString());
    }

    @Override
    public String toString() {
        return "Driver Name Label Change Observer";
    }
}
