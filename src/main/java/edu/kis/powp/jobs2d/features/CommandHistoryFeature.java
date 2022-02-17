package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandHistoryWindow;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.observers.CommandHistoryObserver;

import java.util.logging.Logger;

public class CommandHistoryFeature
{
	private static Application app;
	private static DriverCommandManager driverCommandManager;
	public static CommandHistoryObserver list;
	private static Logger logger =  Logger.getLogger("global");;

	public static void setupHistoryPlugin(Application application)
	{
		app = application;

		driverCommandManager = CommandsFeature.getDriverCommandManager();

		list = new CommandHistoryObserver(driverCommandManager);

		driverCommandManager.getChangePublisher().addSubscriber(list);

		app.addWindowComponent("Loaded History Command", new CommandHistoryWindow(list.getCommandsString(), list.getCommands()));
	}
}


