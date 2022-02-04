package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.events.SelectCommandHistoryListener;
import edu.kis.powp.jobs2d.observers.CommandHistoryObserver;
import edu.kis.powp.observer.Publisher;
import edu.kis.powp.observer.Subscriber;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.logging.Logger;

public class CommandHistoryFeature
{
    private static Application app;
    private static DriverCommandManager driverCommandManager;
    private static CommandHistoryObserver list;
    private static Logger logger =  Logger.getLogger("global");;

    public static final String COMMAND_HISTORY = "Command History";
    public static final String SHOW_COMMAND_HISTORY = "Show command history";

    public static void setupHistoryPlugin(Application application)
    {
        app = application;
        app.addComponentMenu(CommandHistoryFeature.class, COMMAND_HISTORY);
        app.addComponentMenuElement(CommandHistoryFeature.class, SHOW_COMMAND_HISTORY, new SelectCommandHistoryListener());

        driverCommandManager = CommandsFeature.getDriverCommandManager();
        list = new CommandHistoryObserver(driverCommandManager);

        driverCommandManager.getChangePublisher().addSubscriber(list);

    }

    public static void showCommmands()
    {
        logger.info("\nHistory of commands:");
        for (int i = 0; i<list.getCommands().size(); i++)
        {
            logger.info(i+1 +": "+list.getCommands().get(i)+"\n");
        }
    }
}


