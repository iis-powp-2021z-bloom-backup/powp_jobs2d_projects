package edu.kis.powp.jobs2d.transforms;

public class FlipXTransform implements Transform {
	@Override
	public int transformX(int oldX, int oldY) {
		return oldX;
	}

	@Override
	public int transformY(int oldX, int oldY) {
		return oldY * (-1);
	}
}
