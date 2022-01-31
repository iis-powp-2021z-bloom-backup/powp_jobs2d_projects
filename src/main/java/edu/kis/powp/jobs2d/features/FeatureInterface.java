package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import javax.swing.*;

public interface FeatureInterface {

    void setup(Application application, JPanel freePanel, DriverManager drvMgr);

}
