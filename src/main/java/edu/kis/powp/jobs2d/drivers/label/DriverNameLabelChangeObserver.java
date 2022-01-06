package edu.kis.powp.jobs2d.drivers.label;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.observer.Subscriber;

public class DriverNameLabelChangeObserver implements Subscriber {

    private final DriverManager driverManager;
    private final DriverNameLabelChangeManager driverNameLabelChangeManager;

    public DriverNameLabelChangeObserver(DriverManager driverManager, DriverNameLabelChangeManager driverNameLabelChangeManager) {
        this.driverManager = driverManager;
        this.driverNameLabelChangeManager = driverNameLabelChangeManager;
    }

    @Override
    public void update() {
        driverNameLabelChangeManager.setCurrentDriver(driverManager.getCurrentDriver());
    }

    @Override
    public String toString() {
        return "Driver Name Label Change Observer";
    }
}
