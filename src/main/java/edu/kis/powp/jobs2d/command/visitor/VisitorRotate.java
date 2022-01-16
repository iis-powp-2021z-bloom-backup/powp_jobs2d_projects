package edu.kis.powp.jobs2d.command.visitor;

public class VisitorRotate extends TransformationVisitor {
	private final double cos;
	private final double sin;

	public VisitorRotate(double angle) {
		this.cos = Math.cos(Math.toRadians(angle));
		this.sin = Math.sin(Math.toRadians(angle));
	}
	
	public int calculateNewPositionX(int x, int y) {
		return (int) ((x * this.cos) - (y * this.sin));
	}

	public int calculateNewPositionY(int x, int y) {
		return (int) ((x * this.sin) + (y * this.cos));
	}
}
