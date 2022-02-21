package edu.kis.powp.jobs2d.drivers.visitor;


import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.jobs2d.drivers.composite.IDriverComposite;

public class VisitorCounter implements VisitorDriver{
    private int counter = 0;

    public int getCounter()
    {
        return counter;
    }

    @Override
    public void visit(DriverComposite driverComposite) {

    }

    @Override
    public void visit(DriverManager driverManager) {
        counter++;
    }
}
