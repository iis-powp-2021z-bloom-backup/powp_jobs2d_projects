package edu.kis.powp.jobs2d.drivers.composite;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;


public interface IDriverComposite extends Job2dDriver {


    @Override
    void setPosition(int x, int y);
    @Override
    void operateTo(int x, int y);
    void add(Job2dDriver driver);
    void add(LineDriverAdapter driver);
    Job2dDriver getDriver(int k);

}
