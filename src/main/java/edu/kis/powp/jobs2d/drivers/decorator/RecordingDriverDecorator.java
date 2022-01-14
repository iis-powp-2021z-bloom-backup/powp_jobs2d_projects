package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.features.DriverFeature;
import edu.kis.powp.jobs2d.features.RecordingFeature;
import edu.kis.powp.observer.Subscriber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecordingDriverDecorator implements Job2dDriver, Subscriber {
	private final List<DriverCommand> list = new ArrayList<>();
	private Job2dDriver driver;

	public RecordingDriverDecorator(Job2dDriver driver) {
		this.driver = driver;
	}

	@Override
	public void setPosition(int x, int y) {
		list.add(new SetPositionCommand(x, y));
		driver.setPosition(x,y);
	}

	@Override
	public void operateTo(int x, int y) {
		list.add(new OperateToCommand(x, y));
		driver.operateTo(x,y);
	}

	@Override
	public void update() {
		Job2dDriver newDriver = DriverFeature.getDriverManager().getCurrentDriver();
		if(newDriver instanceof RecordingDriverDecorator) {
			return;}

		this.driver = newDriver;
		if(RecordingFeature.isRecording()) {
			DriverFeature.getDriverManager().setCurrentDriver(this);
		}
	}

	@Override
	public String toString() {
		return "Recording Driver Decorator";
	}

	public CompoundCommand getRecording() {
		return new CompoundCommand(list,
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + " Recording");
	}

	public void clearRecording() {
		this.list.clear();
	}

	public Job2dDriver getInnerDriver() {
		return this.driver;
	}
}
