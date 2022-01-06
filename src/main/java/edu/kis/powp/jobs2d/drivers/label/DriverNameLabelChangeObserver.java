package edu.kis.powp.jobs2d.drivers.label;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.observer.Subscriber;

public class DriverNameLabelChangeObserver implements Subscriber {

    private final DriverNameLabelChangeManager driverNameLabelChangeManager;
    private final Application application;

    public DriverNameLabelChangeObserver(DriverNameLabelChangeManager driverNameLabelChangeManager, Application application) {
        this.driverNameLabelChangeManager = driverNameLabelChangeManager;
        this.application = application;
    }

    @Override
    public void update() {
        application.updateInfo(driverNameLabelChangeManager.getCurrentDriverString());
    }

    @Override
    public String toString() {
        return "Driver Name Label Change Observer";
    }
}
