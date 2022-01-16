package edu.kis.powp.jobs2d.command.visitor;

public class VisitorScale extends TransformationVisitor {
	private final double scale;

	public VisitorScale(double scale) {
		this.scale = scale;
	}

	@Override
	int calculateNewPositionX(int oldX, int oldY) {
		return (int) (oldX * this.scale);
	}

	@Override
	int calculateNewPositionY(int oldX, int oldY) {
		return (int) (oldY * this.scale);
	}
}
