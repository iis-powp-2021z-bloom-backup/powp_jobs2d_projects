package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.decorator.RecordingDriverDecorator;
import edu.kis.powp.jobs2d.events.SelectRecordMacroMenuOptionListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RecordingFeature {
	private static Application app;
	private static boolean isRecording = false;
	private static final RecordingDriverDecorator driver =
			new RecordingDriverDecorator(DriverFeature.getDriverManager().getCurrentDriver());

	private static JCheckBoxMenuItem checkbox;

	public static final String RECORD_MACRO = "Record Macro";
	public static final String RECORDING = "Recording";
	public static final String CLEAR_MACRO = "Clear Macro";
	public static final String RUN_MACRO = "Run Macro";
	public static final String ABOUT = "About :)";
	public static final String ABOUT_MSG = "Authors: Pawel Wozniak and Mateusz Janiak";

	private RecordingFeature() {}

	public static void setupRecordingPlugin(Application application) {
		app = application;
		ActionListener listener = new SelectRecordMacroMenuOptionListener();

		app.addComponentMenu(RecordingFeature.class, RECORD_MACRO);
		app.addComponentMenuElementWithCheckBox(RecordingFeature.class, RECORDING, listener, isRecording);
		app.addComponentMenuElement(RecordingFeature.class, CLEAR_MACRO, listener);
		app.addComponentMenuElement(RecordingFeature.class, RUN_MACRO, listener);
		app.addComponentMenuElement(RecordingFeature.class, ABOUT, listener);

		DriverFeature.getDriverManager().addSubscriber(driver);
		checkbox = Arrays.stream(app.getFreePanel().getRootPane().getJMenuBar().getSubElements())
				.flatMap(x -> Arrays.stream(x.getSubElements()))
				.flatMap(x -> Arrays.stream(x.getSubElements()))
				.filter(x -> x instanceof JCheckBoxMenuItem)
				.map(x -> (JCheckBoxMenuItem) x)
				.filter(x -> x.getActionCommand().equals(RECORDING))
				.findFirst()
				.orElseGet(JCheckBoxMenuItem::new);
	}

	public static void register() {
		checkbox.setState(isRecording());

		if(RecordingFeature.isRecording()) {
			DriverFeature.getDriverManager().setCurrentDriver(driver);
		} else {
			DriverFeature.getDriverManager().setCurrentDriver(driver.getInnerDriver());
		}
	}

	public static boolean isRecording() {
		return isRecording;
	}

	public static void stopRecording() {
		isRecording = false;
		register();
	}

	public static void startRecording() {
		isRecording = true;
		register();
	}

	public static void toggleRecording() {
		isRecording = !isRecording;
		register();
	}

	public static RecordingDriverDecorator getRecordingDriver() {
		return driver;
	}
}
