package edu.kis.powp.jobs2d.transforms;

public class ScaleTransform implements Transform {
	private final double scale;

	public ScaleTransform(double scale) {
		this.scale = scale;
	}

	@Override
	public int transformX(int oldX, int oldY) {
		return (int) (oldX * this.scale);
	}

	@Override
	public int transformY(int oldX, int oldY) {
		return (int) (oldY * this.scale);
	}
}
