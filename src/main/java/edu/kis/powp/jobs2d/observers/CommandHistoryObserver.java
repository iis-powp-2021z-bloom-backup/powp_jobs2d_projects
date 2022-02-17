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
	private List<DriverCommand> comm = new ArrayList<>();
	DriverCommandManager manager;
	int listPosition = comm.size();

	public CommandHistoryObserver(DriverCommandManager manager)
	{
		this.manager = manager;
	}

	public List<String> getCommandsString()
	{
		return commands;
	}

	public List<DriverCommand> getCommands()
	{
		return comm;
	}

	public Integer getListPosition(){return listPosition;}

	public void setListPosition(Integer i) {
		this.listPosition = i;
	}

	@Override
	public void update()
	{
		commands.add(manager.getCurrentCommandString());
		comm.add(manager.getCurrentCommand());
		listPosition = comm.size();
	}
}