package edu.kis.powp.jobs2d.transforms;

public class FlipYTransform implements Transform {
	@Override
	public int transformX(int oldX, int oldY) {
		return oldX * (-1);
	}

	@Override
	public int transformY(int oldX, int oldY) {
		return oldY;
	}
}
