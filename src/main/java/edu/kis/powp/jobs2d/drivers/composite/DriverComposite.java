package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.LoggerDriver;
import edu.kis.powp.jobs2d.drivers.adapter.LineDriverAdapter;

import java.util.ArrayList;
import java.util.List;

public class DriverComposite implements IDriverComposite{
    private List<Job2dDriver> drivers;

    public DriverComposite(){
        this.drivers = new ArrayList<Job2dDriver>();
    }
    public DriverComposite(Job2dDriver driver){
        this.drivers = new ArrayList<Job2dDriver>();
        this.drivers.add(driver);
    }
    @Override
    public void setPosition(int x, int y) {
        this.drivers.get(this.drivers.size()-1).setPosition(x,y);

    }

    @Override
    public void operateTo(int x, int y) {
        this.drivers.get(this.drivers.size()-1).operateTo(x,y);

    }

    @Override
    public void add(Job2dDriver driver) {
        this.drivers.add(driver);
    }

    @Override
    public void add(LineDriverAdapter driver) {
        this.drivers.add(driver);
    }

    @Override
    public Job2dDriver getDriver(int k) {
        return this.drivers.get(k);
    }
}
