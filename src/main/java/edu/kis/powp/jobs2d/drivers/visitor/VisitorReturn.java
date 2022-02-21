package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;

public class VisitorReturn implements VisitorDriver{

    private Job2dDriver savedJob2dDriver;

    public Job2dDriver getSavedJob2dDriver() {
        return savedJob2dDriver;
    }

    @Override
    public void visit(DriverComposite driverComposite) {
        savedJob2dDriver = driverComposite.currentDriver;
    }

    @Override
    public void visit(DriverManager driverManager) {
        savedJob2dDriver =  driverManager.currentDriver;
    }
}
