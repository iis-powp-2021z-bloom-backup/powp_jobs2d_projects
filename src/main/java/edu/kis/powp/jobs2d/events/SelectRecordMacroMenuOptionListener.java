package edu.kis.powp.jobs2d.events;

import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.RecordingFeature;

import javax.swing.*;

import static edu.kis.powp.jobs2d.features.RecordingFeature.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectRecordMacroMenuOptionListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case RECORDING: {
				RecordingFeature.toggleRecording();
				break;
			}
			case CLEAR_MACRO: {
				RecordingFeature.getRecordingDriver().clearRecording();
				break;
			}
			case RUN_MACRO: {
				RecordingFeature.stopRecording();
				CommandsFeature.getDriverCommandManager().setCurrentCommand(RecordingFeature.getRecordingDriver().getRecording());
				CommandsFeature.getDriverCommandManager().getCurrentCommand().execute(DriverFeature.getDriverManager().getCurrentDriver());
				break;
			}
			case ABOUT: {
				JOptionPane.showMessageDialog(null, ABOUT_MSG, ABOUT, JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			default: {
			}
		}
	}
}
