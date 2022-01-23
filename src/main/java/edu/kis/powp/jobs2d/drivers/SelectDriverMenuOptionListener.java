package edu.kis.powp.jobs2d.drivers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.composite.DriverComposite;
import edu.kis.powp.jobs2d.features.ExtensionFeature;

public class SelectDriverMenuOptionListener implements ActionListener {
	private DriverManager driverManager;
	private Job2dDriver driver;
	private ExtensionsManager extensionsManager;

	public SelectDriverMenuOptionListener(Job2dDriver driver, DriverManager driverManager) {
		this.extensionsManager = ExtensionFeature.getExtensionsManager();
		this.driverManager = driverManager;
		this.driver = driver;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Job2dDriver> extensions =  extensionsManager.getActiveExtensionList();
		if(extensions.isEmpty()){
			driverManager.setCurrentDriver(driver);
		} else {
			DriverComposite driverComposite = new DriverComposite(driverManager.getCurrentDriver());
			for (Job2dDriver driver: extensions) {
				driverComposite.add(driver);
			}
			driverManager.setCurrentDriver(driverComposite);
		}
	}
}
