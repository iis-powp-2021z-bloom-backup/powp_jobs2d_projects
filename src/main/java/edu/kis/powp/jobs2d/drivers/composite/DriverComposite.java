package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;
import java.util.ArrayList;
import java.util.List;

public class DriverComposite implements IDriverComposite{
	private List<Job2dDriver> drivers;

	public DriverComposite(){
		this.drivers = new ArrayList<>();
	}

	public DriverComposite(Job2dDriver driver){
		this.drivers = new ArrayList<>();
		this.drivers.add(driver);
	}

	public List<Job2dDriver> getDrivers() {
		return drivers;
	}

	@Override
	public void setPosition(int x, int y) {
		for (Job2dDriver driver : drivers) {
			driver.setPosition(x, y);

		}
	}

	@Override
	public void operateTo(int x, int y) {
		for (Job2dDriver driver : drivers) {
			driver.operateTo(x, y);

		}
	}

	@Override
	public void remove(Job2dDriver driver) {
		drivers.remove(driver);
	}

	@Override
	public void add(Job2dDriver driver) {
		this.drivers.add(driver);
	}

	@Override
	public Job2dDriver getDriver(int k) {
		return this.drivers.get(k);
    }

}