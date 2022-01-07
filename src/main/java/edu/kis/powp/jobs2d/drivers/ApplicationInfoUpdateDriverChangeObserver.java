package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.observer.Subscriber;

public class ApplicationInfoUpdateDriverChangeObserver implements Subscriber {
    private final DriverManager driverManager;
    private final Application application;

    public ApplicationInfoUpdateDriverChangeObserver(DriverManager driverManager, Application application) {
        this.driverManager = driverManager;
        this.application = application;
    }

    @Override
    public void update() {
        application.updateInfo(driverManager.getCurrentDriverString());
    }

    @Override
    public String toString() {
        return "Application Info Update Driver Change Observer";
    }
}
