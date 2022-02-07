package edu.kis.powp.jobs2d.features;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.command.manager.LoggerCommandChangeObserver;

public class CommandsFeature implements FeatureInterface {

    private static DriverCommandManager commandManager;

    public CommandsFeature() {
    }

    /**
     * Setup Driver Command Manager.
     * Parameters required by implemented interface, not used here.
     */
    @Override
    public void setup() {
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
