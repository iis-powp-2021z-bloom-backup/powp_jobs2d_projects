package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RecordingDriverDecorator implements Job2dDriver {
	private final List<DriverCommand> list = new ArrayList<>();
	private final DriverManager driverManager;
	private Job2dDriver driver;

	public RecordingDriverDecorator(DriverManager driverManager) {
		this.driverManager = driverManager;
		this.driver = driverManager.getCurrentDriver();
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

	public void setInnerDriver(Job2dDriver driver) {
		this.driver = driver;
	}
}
