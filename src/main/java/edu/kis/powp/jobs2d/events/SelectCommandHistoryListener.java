package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.features.CommandHistoryFeature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class SelectCommandHistoryListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        Logger logger = Logger.getLogger("global");
        CommandHistoryFeature.showCommmands();
    }
}
