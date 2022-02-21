package edu.kis.powp.jobs2d.drivers.composite;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.visitor.VisitorDriver;

import java.util.ArrayList;
import java.util.List;

public class DriverComposite implements IDriverComposite{
	private List<Job2dDriver> drivers;
	public Job2dDriver currentDriver;
	public DriverComposite(){
		this.drivers = new ArrayList<Job2dDriver>();
	}
	public DriverComposite(Job2dDriver driver){
		this.drivers = new ArrayList<Job2dDriver>();
		this.drivers.add(driver);
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
	public void accept(VisitorDriver visitor) {
		visitor.visit(this);
	}

	@Override
	public void add(Job2dDriver driver) {
		this.drivers.add(driver);
	}

}