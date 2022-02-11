package edu.kis.powp.jobs2d.observers;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class CommandHistoryObserver implements Subscriber
{
	private List<String> commands = new ArrayList<>();
	DriverCommandManager manager;

	public CommandHistoryObserver(DriverCommandManager manager)
	{
		this.manager = manager;
	}

	public List<String> getCommands()
	{
		return commands;
	}

	@Override
	public void update()
	{
		commands.add(manager.getCurrentCommandString());
	}
}