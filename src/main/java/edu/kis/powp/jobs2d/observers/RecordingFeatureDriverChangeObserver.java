package edu.kis.powp.jobs2d.observers;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.decorator.RecordingDriverDecorator;
import edu.kis.powp.jobs2d.features.RecordingFeature;
import edu.kis.powp.observer.Subscriber;

public class RecordingFeatureDriverChangeObserver implements Subscriber {
	private final DriverManager driverManager;
	private final RecordingDriverDecorator recordingDriver;

	public RecordingFeatureDriverChangeObserver(DriverManager driverManager, RecordingDriverDecorator driver) {
		this.driverManager = driverManager;
		this.recordingDriver = driver;
	}

	@Override
	public void update() {
		Job2dDriver newDriver = driverManager.getCurrentDriver();
		if(newDriver instanceof RecordingDriverDecorator) {
			return;}

		recordingDriver.setInnerDriver(newDriver);
		if(RecordingFeature.isRecording()) {
			driverManager.setCurrentDriver(recordingDriver);
		}
	}
}
