package edu.kis.powp.jobs2d.events;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.gui.CommandHistoryWindow;
import edu.kis.powp.jobs2d.command.manager.DriverCommandManager;
import edu.kis.powp.jobs2d.features.CommandHistoryFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SelectCommandHistoryListener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		CommandHistoryFeature.showCommmands();
	}
}
