package edu.kis.powp.jobs2d.drivers.visitor;

import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;


public interface VisitorDriver {
    void visit(DriverComposite driverComposite);

    void visit(DriverManager driverManager);
}
