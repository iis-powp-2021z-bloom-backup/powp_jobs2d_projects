package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.drivers.ApplicationInfoUpdateDriverChangeObserver;

import javax.swing.*;

public class DriverFeature implements FeatureInterface {

    private static DriverManager driverManager = new DriverManager();
    private static Application app;

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    /**
     * Setup jobs2d drivers Plugin and add to application.
     * Parameters required by implemented interface, freePanel and driverManager not used here.
     *
     * @param application Application context.
     * @param freePanel   Free application panel.
     * @param drvMgr      Driver manager object.
     */
    @Override
    public void setup(Application application, JPanel freePanel, DriverManager drvMgr) {
        app = application;
        app.addComponentMenu(DriverFeature.class, "Drivers");
    }

    /**
     * Add driver to context, create button in driver menu.
     *
     * @param name   Button name.
     * @param driver Job2dDriver object.
     */

    public static void addDriver(String name, Job2dDriver driver) {
        SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
        app.addComponentMenuElement(DriverFeature.class, name, listener);
    }

    public void setUpDriverNameLabelChangeManager() {
        ApplicationInfoUpdateDriverChangeObserver applicationInfoUpdateDriverChangeObserver = new ApplicationInfoUpdateDriverChangeObserver(driverManager, app);
        driverManager.getChangePublisher().addSubscriber(applicationInfoUpdateDriverChangeObserver);
    }


}
