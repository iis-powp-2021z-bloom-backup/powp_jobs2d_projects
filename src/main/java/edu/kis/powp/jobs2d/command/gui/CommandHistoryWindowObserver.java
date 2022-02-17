package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.observers.CommandHistoryObserver;
import edu.kis.powp.observer.Subscriber;

public class CommandHistoryWindowObserver extends CommandHistoryObserver {

    public CommandHistoryWindowObserver(DriverCommandManager manager) {
        super(manager);
    }

    @Override
    public void update() {
        super.update();
    }
}
