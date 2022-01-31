package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import javax.swing.*;

public class CommandsFeature implements FeatureInterface{

	private static DriverCommandManager commandManager;

	@Override
	public void setup(Application application, JPanel freePanel, DriverManager drvMgr) {
		commandManager = new DriverCommandManager();
		commandManager.setDriverManager(DriverFeature.getDriverManager());
		LoggerCommandChangeObserver loggerObserver = new LoggerCommandChangeObserver();
		commandManager.getChangePublisher().addSubscriber(loggerObserver);
	}

	/**
	 * Get manager of application driver command.
	 * 
	 * @return plotterCommandManager.
	 */
	public static DriverCommandManager getDriverCommandManager() {
		return commandManager;
	}

}
