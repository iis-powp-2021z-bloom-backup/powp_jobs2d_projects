package edu.kis.powp.jobs2d.command.manager;


import edu.kis.powp.observer.Publisher;

/**
 * Manager class for the device.
 * Can be used by multiple drivers
 */
public class DeviceUsageManager {
	private final Publisher publisher = new Publisher();

	private double distanceTravelled = 0.0;
	private double distanceDeviceUsed = 0.0;

	public void notifyHeadMovePerformed(double distance) {
		this.distanceTravelled += distance;
		this.publisher.notifyObservers();
	}

	public void notifyOperationPerformed(double distance) {
		this.distanceDeviceUsed += distance;
		this.notifyHeadMovePerformed(distance);
		this.publisher.notifyObservers();

	}

	public void clearDistanceTravelled() {
		this.distanceTravelled = 0.0;
		this.publisher.notifyObservers();
	}

	public void clearDistanceUsed() {
		this.distanceDeviceUsed = 0.0;
		this.publisher.notifyObservers();
	}

	public double getDistanceTravelled() {
		return distanceTravelled;
	}

	public double getDistanceDeviceUsed() {
		return distanceDeviceUsed;
	}

	public Publisher getPublisher() {
		return publisher;
	}
}
