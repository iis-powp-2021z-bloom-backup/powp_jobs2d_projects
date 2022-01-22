package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.decorator.RecordingDriverDecorator;
import edu.kis.powp.jobs2d.events.SelectRecordMacroMenuOptionListener;
import edu.kis.powp.jobs2d.observers.RecordingFeatureDriverChangeObserver;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RecordingFeature {
	private static boolean isRecording = false;
	private static RecordingDriverDecorator recordingDriver;
	private static DriverManager driverManager;

	private static JCheckBoxMenuItem checkbox;

	public static final String RECORD_MACRO = "Record Macro";
	public static final String RECORDING = "Recording";
	public static final String CLEAR_MACRO = "Clear Macro";
	public static final String RUN_MACRO = "Run Macro";

	private RecordingFeature() {}

	public static void setupRecordingPlugin(Application app, DriverManager drvMgr) {
		ActionListener listener = new SelectRecordMacroMenuOptionListener();

		driverManager = drvMgr;
		recordingDriver = new RecordingDriverDecorator(driverManager);

		app.addComponentMenu(RecordingFeature.class, RECORD_MACRO);
		app.addComponentMenuElementWithCheckBox(RecordingFeature.class, RECORDING, listener, isRecording);
		app.addComponentMenuElement(RecordingFeature.class, CLEAR_MACRO, listener);
		app.addComponentMenuElement(RecordingFeature.class, RUN_MACRO, listener);

		driverManager.getChangePublisher()
				.addSubscriber(new RecordingFeatureDriverChangeObserver(driverManager, recordingDriver));
		checkbox = Arrays.stream(app.getFreePanel().getRootPane().getJMenuBar().getSubElements())
				.flatMap(x -> Arrays.stream(x.getSubElements()))
				.flatMap(x -> Arrays.stream(x.getSubElements()))
				.filter(x -> x instanceof JCheckBoxMenuItem)
				.map(x -> (JCheckBoxMenuItem) x)
				.filter(x -> x.getActionCommand().equals(RECORDING))
				.findFirst()
				.orElseGet(JCheckBoxMenuItem::new);
	}

	private static void updateCurrentDriver() {
		checkbox.setState(isRecording());

		if(isRecording()) {
			driverManager.setCurrentDriver(recordingDriver);
		} else {
			driverManager.setCurrentDriver(recordingDriver.getInnerDriver());
		}
	}

	public static boolean isRecording() {
		return isRecording;
	}

	public static void stopRecording() {
		isRecording = false;
		updateCurrentDriver();
	}

	public static void startRecording() {
		isRecording = true;
		updateCurrentDriver();
	}

	public static void toggleRecording() {
		isRecording = !isRecording;
		updateCurrentDriver();
	}

	public static RecordingDriverDecorator getRecordingDriver() {
		return recordingDriver;
	}
}
