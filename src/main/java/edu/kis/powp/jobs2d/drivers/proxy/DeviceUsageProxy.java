package edu.kis.powp.jobs2d.drivers.proxy;

import edu.kis.powp.jobs2d.Job2dDriver;

import java.util.logging.Logger;

public class DeviceUsageProxy implements Job2dDriver {
    private final Job2dDriver driver;
    private final Logger logger = Logger.getLogger("global");
    private int posX = 0;
    private int posY = 0;
    private double distance = 0.0;
    private double operatingDistance = 0.0;

    public DeviceUsageProxy(Job2dDriver driver) {
        this.driver = driver;
    }

    @Override
    public void setPosition(int x, int y) {
        driver.setPosition(x, y);
        calculateDistance(x, y);
    }

    @Override
    public void operateTo(int x, int y) {
        driver.operateTo(x, y);
        operatingDistance += calculateDistance(x, y);
        logDeviceUsage();
    }

    private double calculateDistance(int x, int y) {
        double lastOperationDist = Math.sqrt(Math.pow(x - posX, 2) + Math.pow(y - posY, 2));
        distance += lastOperationDist;
        posX = x;
        posY = y;
        return lastOperationDist;
    }

    private void logDeviceUsage() {
        this.logger.info("distance: " + distance);
        this.logger.info("operating distance: " + operatingDistance);
    }
}
