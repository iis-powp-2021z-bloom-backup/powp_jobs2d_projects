package edu.kis.powp.jobs2d.transforms;

public class RotateTransform implements Transform {
	private final double cos;
	private final double sin;

	public RotateTransform(double angle) {
		this.cos = Math.cos(Math.toRadians(angle));
		this.sin = Math.sin(Math.toRadians(angle));
	}

	public int transformX(int x, int y) {
		return (int) ((x * this.cos) - (y * this.sin));
	}

	public int transformY(int x, int y) {
		return (int) ((x * this.sin) + (y * this.cos));
	}
}
